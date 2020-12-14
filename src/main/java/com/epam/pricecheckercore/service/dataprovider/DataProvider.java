package com.epam.pricecheckercore.service.dataprovider;

import com.epam.pricecheckercore.model.product.Product;
import com.epam.pricecheckercore.model.product.ProductData;
import org.jsoup.nodes.Document;

public interface DataProvider {

    Product getProduct();

    ProductData getProductData(Document document);
}
