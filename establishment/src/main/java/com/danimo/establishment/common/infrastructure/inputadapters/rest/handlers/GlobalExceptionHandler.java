package com.danimo.establishment.common.infrastructure.inputadapters.rest.handlers;

import com.danimo.establishment.common.application.exceptions.EstablishmentAlreadyExistException;
import com.danimo.establishment.common.application.exceptions.LocationNotFoundException;
import com.danimo.establishment.common.infrastructure.inputadapters.rest.dto.RestApiError;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Hidden
public class GlobalExceptionHandler {

    @ExceptionHandler(EstablishmentAlreadyExistException.class)
    public ResponseEntity<RestApiError> handleUserNotFoundException(EstablishmentAlreadyExistException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new RestApiError(HttpStatus.CONFLICT.value(), e.getMessage()));
    }
    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<RestApiError> handleLocationNotFoundException(LocationNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new RestApiError(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RestApiError> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new RestApiError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }
}
