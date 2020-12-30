package com.epam.pricecheckercore.service.dataprovider;

import java.net.MalformedURLException;

public interface ApiUrlConstructor {

    String construct(String initialUrl) throws MalformedURLException;
}
