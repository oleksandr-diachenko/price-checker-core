package com.epam.pricecheckercore.helper.stringdecorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerStringProcessor implements StringProcessor {

    @Override
    public String process(String string) {
        log.info("Processed string: {}", string);
        return string;
    }
}
