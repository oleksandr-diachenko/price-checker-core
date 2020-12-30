package com.epam.pricecheckercore.service.dataprovider;

import com.epam.pricecheckercore.service.parser.Parser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
public class FileJsonParser implements Parser<String> {

    @Override
    public Optional<String> getContent(String path) {
        try {
            String content = IOUtils.toString(getClass().getResourceAsStream(path), UTF_8);
            return Optional.of(content);
        } catch (IOException e) {
            log.error("Can't parse document: {}", path, e);
            return Optional.empty();
        }
    }
}
