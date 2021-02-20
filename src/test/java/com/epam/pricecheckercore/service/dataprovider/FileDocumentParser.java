package com.epam.pricecheckercore.service.dataprovider;

import com.epam.pricecheckercore.service.parser.Parser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Slf4j
public class FileDocumentParser implements Parser<Document> {

    @Override
    public Optional<Document> getContent(String path) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(path);
        if (is == null) {
            log.error("Can't read file: {}", path);
            return Optional.empty();
        }
        try {
            Document document = Jsoup.parse(is, "UTF-8", StringUtils.EMPTY);
            return Optional.of(document);
        } catch (IOException e) {
            log.error("Can't parse document: {}", path, e);
            return Optional.empty();
        }
    }
}
