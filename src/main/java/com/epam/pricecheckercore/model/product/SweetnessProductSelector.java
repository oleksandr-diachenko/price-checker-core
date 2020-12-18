package com.epam.pricecheckercore.model.product;

import com.epam.pricecheckercore.model.enums.CurrencyCode;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.UAH;

public class SweetnessProductSelector implements ProductSelector {

    @Override
    public String getInStockSelector() {
        return "#buy_block #add_to_cart";
    }

    @Override
    public String getDiscountedPriceSelector() {
        return ".content_prices_row.has-discount #our_price_display";
    }

    @Override
    public String getNormalPriceSelector() {
        return ".content_prices_row:not(.has-discount) #our_price_display , #old_price_display";
    }

    @Override
    public CurrencyCode getCurrencyCode() {
        return UAH;
    }
}
