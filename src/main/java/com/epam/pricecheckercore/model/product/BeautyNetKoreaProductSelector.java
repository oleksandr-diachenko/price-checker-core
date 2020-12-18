package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.model.enums.CurrencyCode;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.USD;

public class BeautyNetKoreaProductSelector implements ProductSelector {

    @Override
    public String getInStockSelector() {
        return "a:not(.displaynone):contains(Add to Cart)";
    }

    @Override
    public String getDiscountedPriceSelector() {
        return "#span_product_price_sale";
    }

    @Override
    public String getNormalPriceSelector() {
        return "#span_product_price_text";
    }

    @Override
    public CurrencyCode getCurrencyCode() {
        return USD;
    }
}
