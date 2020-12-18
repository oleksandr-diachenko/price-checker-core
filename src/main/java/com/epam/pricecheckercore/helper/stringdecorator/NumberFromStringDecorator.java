package com.epam.pricecheckercore.helper.stringdecorator;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class NumberFromStringDecorator extends AbstractStringDecorator {

    private static final Pattern numberPattern = Pattern.compile("\\d+.\\d+|\\d+"); // int or double

    public NumberFromStringDecorator(StringProcessor stringDecorator) {
        super(stringDecorator);
    }

    @Override
    public String process(String string) {
        String result = string;
        Matcher matcher = numberPattern.matcher(result);
        while (matcher.find()) {
            result = matcher.group();
        }
        return super.process(result);
    }
}
