package com.danimo.establishment.common.infrastructure.inputadapters.rest.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<RestApiError> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new RestApiError(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }*/
}
