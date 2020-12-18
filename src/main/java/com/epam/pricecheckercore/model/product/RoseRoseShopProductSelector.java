package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.model.enums.CurrencyCode;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.USD;

public class RoseRoseShopProductSelector implements ProductSelector {

    @Override
    public String getInStockSelector() {
        return "li:contains(In Stock)";
    }

    @Override
    public String getDiscountedPriceSelector() {
        return ".price-new";
    }

    @Override
    public String getNormalPriceSelector() {
        return ".price-old , .price:not(:has(span))";
    }

    @Override
    public CurrencyCode getCurrencyCode() {
        return USD;
    }
}
