package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.model.enums.CurrencyCode;

public interface ProductSelector {

    String getInStockSelector();

    String getDiscountedPriceSelector();

    String getNormalPriceSelector();

    CurrencyCode getCurrencyCode();
}
