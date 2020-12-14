package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.model.enums.CurrencyCode;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.UAH;

public class MakeupProductSelector implements ProductSelector {

    @Override
    public String getInStockSelector() {
        return ".product-item__status.green";
    }

    @Override
    public String getDiscountedPriceSelector() {
        return ".product-item__price-wrap:has(.product-item__old-price:not(.empty)) [itemprop=price]";
    }

    @Override
    public String getNormalPriceSelector() {
        return ".product-item__price-wrap:has(.product-item__old-price:not(.empty)) .product-item__old-price , [itemprop=price]";
    }

    @Override
    public CurrencyCode getCurrencyCode() {
        return UAH;
    }
}
