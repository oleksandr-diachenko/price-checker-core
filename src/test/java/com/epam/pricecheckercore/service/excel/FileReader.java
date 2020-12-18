package com.epam.pricecheckercore.service.excel;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class FileReader {

    public byte[] getBytes(String fileName) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        try {
            return Objects.requireNonNull(resource).openStream().readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't read file", e);
        }
    }
}