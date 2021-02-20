package com.epam.pricecheckercore.service.dataprovider;

public class MockApiUrlConstructor implements ApiUrlConstructor {

    @Override
    public String construct(String initialUrl) {
        return initialUrl;
    }
}
