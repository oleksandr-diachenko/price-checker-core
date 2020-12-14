package com.epam.pricecheckercore.helper.stringdecorator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractStringDecorator implements StringProcessor {

    private final StringProcessor stringProcessor;

    @Override
    public String process(String string) {
        return stringProcessor.process(string);
    }
}
