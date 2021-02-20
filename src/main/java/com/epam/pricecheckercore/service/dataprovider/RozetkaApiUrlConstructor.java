package com.epam.pricecheckercore.service.dataprovider;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;

@RequiredArgsConstructor
public class RozetkaApiUrlConstructor implements ApiUrlConstructor {

    private final String baseUrl;

    @Override
    public String construct(String initialUrl) throws MalformedURLException {
        String[] parts = initialUrl.split("/");
        if(parts.length < 3) {
            throw new MalformedURLException();
        }
        String idPart = parts[parts.length - 1];
        String id = StringUtils.removeStart(idPart, "p");
        return baseUrl + id;
    }
}
