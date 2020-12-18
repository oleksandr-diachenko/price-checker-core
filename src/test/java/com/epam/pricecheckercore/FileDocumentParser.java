package com.epam.pricecheckercore;

import com.epam.pricecheckercore.service.parser.DocumentParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Slf4j
public class FileDocumentParser implements DocumentParser {

    @Override
    public Optional<Document> getDocument(String path) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(path);
        try {
            Document document = Jsoup.parse(is, "UTF-8", StringUtils.EMPTY);
            return Optional.of(document);
        } catch (IOException e) {
            log.error("Can't parse document: {}", path, e);
            return Optional.empty();
        }
    }
}
