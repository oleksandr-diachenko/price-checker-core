package com.epam.pricecheckercore.model.enums;

import lombok.Getter;

public enum ProductStatus {

    OUT_OF_STOCK("Нема в наявності"),
    PRICE_NOT_FOUND("Ціна не знайдена"),
    AVAILABLE("available");

    @Getter
    private final String status;

    ProductStatus(String status) {
        this.status = status;
    }
}
