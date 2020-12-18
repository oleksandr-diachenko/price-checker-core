package com.epam.pricecheckercore.helper.stringdecorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RemoveSpacesStringDecorator extends AbstractStringDecorator {

    public RemoveSpacesStringDecorator(StringProcessor stringDecorator) {
        super(stringDecorator);
    }

    @Override
    public String process(String string) {
        String result = string.replace(" ", "");
        return super.process(result);
    }
}
