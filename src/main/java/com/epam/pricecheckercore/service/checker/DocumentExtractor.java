package com.epam.pricecheckercore.service.checker;

import com.epam.pricecheckercore.model.product.ProductData;
import com.epam.pricecheckercore.service.dataprovider.DataProvider;
import com.epam.pricecheckercore.service.parser.DocumentParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;

import java.util.Optional;

import static com.epam.pricecheckercore.model.enums.PageStatus.PAGE_NOT_FOUND;
import static com.epam.pricecheckercore.model.enums.ProductStatus.OUT_OF_STOCK;
import static com.epam.pricecheckercore.model.enums.ProductStatus.PRICE_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
public class DocumentExtractor implements Extractor {

    private final DocumentParser documentParser;

    @Override
    public String extract(DataProvider dataProvider, String url) {
        Optional<Document> document = documentParser.getDocument(url);
        if (document.isEmpty()) {
            log.error("Page with url: {} not found", url);
            return PAGE_NOT_FOUND.getStatus();
        }

        ProductData productData = dataProvider.getProductData(document.get());
        if (!productData.isInStock()) {
            log.error("Product url: {} is out of stock", url);
            return OUT_OF_STOCK.getStatus();
        }

        if (!productData.isPriceFound()) {
            log.error("Price for product: {} not found", url);
            return PRICE_NOT_FOUND.getStatus();
        }
        return String.valueOf(getPrice(productData));
    }

    private Number getPrice(ProductData productData) {
        if (productData.isDiscountedPresent()) {
            return productData.getDiscountedPrice().getNumber();
        } else {
            return productData.getNormalPrice().getNumber();
        }
    }
}