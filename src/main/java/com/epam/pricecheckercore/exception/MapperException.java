package com.epam.pricecheckercore.exception;

public class MapperException extends Exception {

    public MapperException() {
    }

    public MapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public MapperException(Throwable cause) {
        super(cause);
    }
}
