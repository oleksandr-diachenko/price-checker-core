package com.epam.pricecheckercore.service.dataprovider;

import com.epam.pricecheckercore.exception.MapperException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonMapper implements Mapper {

    @Override
    public <T> T map(String content, Class<T> aClass) throws MapperException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(content, aClass);
        } catch (JsonProcessingException e) {
            log.error("Can't map {} to {}", content, aClass);
            throw new MapperException(e);
        }
    }
}
