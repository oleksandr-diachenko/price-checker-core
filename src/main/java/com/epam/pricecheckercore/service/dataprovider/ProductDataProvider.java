package com.epam.pricecheckercore.service.dataprovider;

import com.epam.pricecheckercore.model.product.Product;
import com.epam.pricecheckercore.model.product.ProductData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;

@Slf4j
@RequiredArgsConstructor
public class ProductDataProvider implements DataProvider {

    private final Product product;

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public ProductData getProductData(Document document) {
        ProductData productData = new ProductData();

        if (product.isInStock(document)) {
            productData.setInStock(true);
            productData.setDiscountedPrice(product.getDiscountedPrice(document));
            productData.setNormalPrice(product.getNormalPrice(document));
        }

        return productData;
    }

}