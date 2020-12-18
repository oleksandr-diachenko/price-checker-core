package com.epam.pricecheckercore.model.inputoutput;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CheckerInput {

    private final byte[] file;
    private final int urlIndex;
    private final int insertIndex;
}
