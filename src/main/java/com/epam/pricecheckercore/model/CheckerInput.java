package com.epam.pricecheckercore.model;

import lombok.Builder;
import lombok.Getter;

/**
 * @author : Oleksandr Diachenko
 * @since : 10/27/2020
 **/
@Builder
@Getter
public class CheckerInput {

    private final byte[] file;
    private final int urlIndex;
    private final int insertIndex;
}
