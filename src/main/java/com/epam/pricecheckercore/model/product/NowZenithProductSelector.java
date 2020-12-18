package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.model.enums.CurrencyCode;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.USD;

public class NowZenithProductSelector implements ProductSelector {

    @Override
    public String getInStockSelector() {
        return "li:contains(In Stock)";
    }

    @Override
    public String getDiscountedPriceSelector() {
        return ".special-price";
    }

    @Override
    public String getNormalPriceSelector() {
        return ".product-price , .old-price";
    }

    @Override
    public CurrencyCode getCurrencyCode() {
        return USD;
    }
}
