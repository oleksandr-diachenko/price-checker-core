package com.epam.pricecheckercore.model.product;

import lombok.Data;
import org.javamoney.moneta.Money;

import static com.epam.pricecheckercore.model.enums.CurrencyCode.UAH;

@Data
public class ProductData {

    private boolean isInStock;
    private Money normalPrice = Money.of(0, UAH.name());
    private Money discountedPrice = Money.of(0, UAH.name());

    public boolean isDiscountedPresent() {
        return discountedPrice.isPositive();
    }

    public boolean isPriceFound() {
        return normalPrice.isPositive() || discountedPrice.isPositive();
    }
}
