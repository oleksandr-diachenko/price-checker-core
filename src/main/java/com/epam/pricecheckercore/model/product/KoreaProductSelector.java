package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.model.enums.CurrencyCode;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.UAH;

public class KoreaProductSelector implements ProductSelector {

    @Override
    public String getInStockSelector() {
        return ".in-stock";
    }

    @Override
    public String getDiscountedPriceSelector() {
        return "ins > span.woocommerce-Price-amount";
    }

    @Override
    public String getNormalPriceSelector() {
        return ".woocommerce-Price-amount";
    }

    @Override
    public CurrencyCode getCurrencyCode() {
        return UAH;
    }
}
