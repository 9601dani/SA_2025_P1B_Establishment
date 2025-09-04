package com.danimo.establishment.common.application.exceptions;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(String message) {
        super(message);
    }

    public LocationNotFoundException(Class clazz) {
        super(String.format("Entity of type {} not found", clazz.getCanonicalName()));
    }
}
