package com.epam.pricecheckercore.service.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class HtmlDocumentParser implements DocumentParser {

    @Override
    public Optional<Document> getDocument(String url) {
        try {
            return ofNullable(Jsoup
                    .connect(url)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .get());
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
