package com.epam.pricecheckercore.service.parser;

import org.jsoup.nodes.Document;

import java.util.Optional;

public interface DocumentParser {

    Optional<Document> getDocument(String url);
}
