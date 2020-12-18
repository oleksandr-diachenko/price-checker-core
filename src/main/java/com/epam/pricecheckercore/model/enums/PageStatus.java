package com.epam.pricecheckercore.model.enums;

import lombok.Getter;

public enum PageStatus {

    PAGE_NOT_FOUND("Сторінка не знайдена");

    @Getter
    private final String status;

    PageStatus(String status) {
        this.status = status;
    }
}
