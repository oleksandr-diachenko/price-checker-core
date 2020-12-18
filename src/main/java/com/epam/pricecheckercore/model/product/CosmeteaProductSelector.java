package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.model.enums.CurrencyCode;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.UAH;

public class CosmeteaProductSelector implements ProductSelector {

    @Override
    public String getInStockSelector() {
        return "#button-cart:not([disabled])";
    }

    @Override
    public String getDiscountedPriceSelector() {
        return ".autocalc-product-special";
    }

    @Override
    public String getNormalPriceSelector() {
        return ".autocalc-product-price";
    }

    @Override
    public CurrencyCode getCurrencyCode() {
        return UAH;
    }
}
