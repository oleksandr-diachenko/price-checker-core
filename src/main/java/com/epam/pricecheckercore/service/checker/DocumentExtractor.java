package com.epam.pricecheckercore.service.checker;

import com.epam.pricecheckercore.exception.ProductNotFoundException;
import com.epam.pricecheckercore.model.product.ProductData;
import com.epam.pricecheckercore.service.dataprovider.DataProvider;
import lombok.extern.slf4j.Slf4j;

import static com.epam.pricecheckercore.model.enums.PageStatus.PAGE_NOT_FOUND;
import static com.epam.pricecheckercore.model.enums.ProductStatus.OUT_OF_STOCK;
import static com.epam.pricecheckercore.model.enums.ProductStatus.PRICE_NOT_FOUND;

@Slf4j
public class DocumentExtractor implements Extractor {

    @Override
    public String extract(DataProvider dataProvider, String url) {
        ProductData productData;
        try {
            productData = dataProvider.getProductData(url);
        } catch (ProductNotFoundException e) {
            log.error("Page with url: {} not found", url);
            return PAGE_NOT_FOUND.getStatus();
        }

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

    private double getPrice(ProductData productData) {
        if (productData.isDiscountedPresent()) {
            return productData.getDiscountedPrice().getNumber().doubleValueExact();
        } else {
            return productData.getNormalPrice().getNumber().doubleValueExact();
        }
    }
}