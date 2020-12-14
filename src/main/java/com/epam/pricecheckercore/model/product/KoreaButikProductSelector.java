package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.model.enums.CurrencyCode;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.UAH;

public class KoreaButikProductSelector implements ProductSelector {

    @Override
    public String getInStockSelector() {
        return "[data-qaid=buy-button]";
    }

    @Override
    public String getDiscountedPriceSelector() {
        return "p[data-qaid=product_price]";
    }

    @Override
    public String getNormalPriceSelector() {
        return "[data-qaid=old_product_price] , span[data-qaid=product_price]";
    }

    @Override
    public CurrencyCode getCurrencyCode() {
        return UAH;
    }
}
