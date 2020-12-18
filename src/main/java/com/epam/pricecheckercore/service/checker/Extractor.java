package com.epam.pricecheckercore.service.checker;

import com.epam.pricecheckercore.service.dataprovider.DataProvider;

public interface Extractor {
    String extract(DataProvider dataProvider, String url);
}
