package com.epam.pricecheckercore.helper.stringdecorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReplaceCommaToDotStringDecorator extends AbstractStringDecorator {

    public ReplaceCommaToDotStringDecorator(StringProcessor stringDecorator) {
        super(stringDecorator);
    }

    @Override
    public String process(String string) {
        String result = string.replace(",", ".");
        return super.process(result);
    }
}
