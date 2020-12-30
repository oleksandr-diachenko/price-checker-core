package com.epam.pricecheckercore.service.dataprovider;

import com.epam.pricecheckercore.exception.ProductNotFoundException;
import com.epam.pricecheckercore.helper.stringdecorator.StringProcessor;
import com.epam.pricecheckercore.model.magazine.Magazine;
import com.epam.pricecheckercore.model.product.ProductData;
import com.epam.pricecheckercore.model.product.ProductSelector;
import com.epam.pricecheckercore.service.parser.Parser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javamoney.moneta.Money;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Optional.ofNullable;

@Slf4j
@RequiredArgsConstructor
public class DocumentDataProvider implements DataProvider {

    private final Parser<Document> parser;
    private final Magazine magazine;
    private final ProductSelector productSelector;
    private final StringProcessor stringProcessor;

    @Override
    public Magazine getMagazine() {
        return magazine;
    }

    @Override
    public ProductData getProductData(String url) throws ProductNotFoundException {
        Optional<Document> documentOptional = parser.getContent(url);
        if (documentOptional.isEmpty()) {
            throw new ProductNotFoundException();
        }
        Document document = documentOptional.get();
        ProductData productData = new ProductData();

        if (isInStock(document)) {
            productData.setInStock(true);
            productData.setDiscountedPrice(getDiscountedPrice(document));
            productData.setNormalPrice(getNormalPrice(document));
        }

        return productData;
    }


    private boolean isInStock(Document document) {
        return ofNullable(document.selectFirst(productSelector.getInStockSelector()))
                .isPresent();
    }

    private Money getDiscountedPrice(Document document) {
        return getMoney(document, productSelector.getDiscountedPriceSelector());
    }

    private Money getNormalPrice(Document document) {
        return getMoney(document, productSelector.getNormalPriceSelector());
    }

    private Money getMoney(Document document, String discountPriceSelector) {
        return ofNullable(document.selectFirst(discountPriceSelector))
                .map(Element::text)
                .map(stringProcessor::process)
                .map(toBigDecimal())
                .map(money -> Money.of(money, productSelector.getCurrencyCode().name()))
                .orElse(Money.of(0, productSelector.getCurrencyCode().name()));
    }

    private Function<String, BigDecimal> toBigDecimal() {
        return price -> {
            try {
                return new BigDecimal(price);
            } catch (NumberFormatException e) {
                log.error("Can't convert {} to number, returning 0", price);
                return BigDecimal.ZERO;
            }
        };
    }

}