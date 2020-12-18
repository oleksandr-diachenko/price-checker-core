package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.model.enums.CurrencyCode;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.UAH;

public class RozetkaProductSelector implements ProductSelector {

    @Override
    public String getInStockSelector() {
        return ".product__status.product__status_color_green";
    }

    @Override
    public String getDiscountedPriceSelector() {
        return ".product-prices__big.product-prices__big_color_red";
    }

    @Override
    public String getNormalPriceSelector() {
        return ".product-prices__small , .product-prices__big:not(.product-prices__big_color_red)";
    }

    @Override
    public CurrencyCode getCurrencyCode() {
        return UAH;
    }
}
