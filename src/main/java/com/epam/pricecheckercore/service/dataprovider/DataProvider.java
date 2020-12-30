package com.epam.pricecheckercore.service.dataprovider;

import com.epam.pricecheckercore.exception.ProductNotFoundException;
import com.epam.pricecheckercore.model.magazine.Magazine;
import com.epam.pricecheckercore.model.product.ProductData;

public interface DataProvider {

    Magazine getMagazine();

    ProductData getProductData(String url) throws ProductNotFoundException;
}
