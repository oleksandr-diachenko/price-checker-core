package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.helper.stringdecorator.StringProcessor;
import com.epam.pricecheckercore.model.magazine.Magazine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javamoney.moneta.Money;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.math.BigDecimal;
import java.util.function.Function;

import static java.util.Optional.ofNullable;

@Slf4j
@RequiredArgsConstructor
public class HtmlProduct implements Product {

    private final ProductSelector productSelector;
    private final Magazine magazine;
    private final StringProcessor stringDecorator;

    @Override
    public Magazine getMagazine() {
        return magazine;
    }

    @Override
    public boolean isInStock(Document document) {
        return ofNullable(document.selectFirst(productSelector.getInStockSelector()))
                .isPresent();
    }

    @Override
    public Money getDiscountedPrice(Document document) {
        return getMoney(document, productSelector.getDiscountedPriceSelector());
    }

    @Override
    public Money getNormalPrice(Document document) {
        return getMoney(document, productSelector.getNormalPriceSelector());
    }

    private Money getMoney(Document document, String discountPriceSelector) {
        return ofNullable(document.selectFirst(discountPriceSelector))
                .map(Element::text)
                .map(stringDecorator::process)
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
