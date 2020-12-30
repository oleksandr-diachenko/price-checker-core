package com.epam.pricecheckercore.service.parser;

import java.util.Optional;

public interface Parser<T> {

    Optional<T> getContent(String url);
}
