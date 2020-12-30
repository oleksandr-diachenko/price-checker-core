package com.epam.pricecheckercore.service.dataprovider;

import com.epam.pricecheckercore.exception.MapperException;

public interface Mapper {

    <T> T map(String content, Class<T> aClass) throws MapperException;
}
