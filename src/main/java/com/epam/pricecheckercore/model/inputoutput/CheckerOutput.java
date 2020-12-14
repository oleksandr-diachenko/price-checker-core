package com.epam.pricecheckercore.model.inputoutput;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class CheckerOutput {

    private final byte[] file;
}
