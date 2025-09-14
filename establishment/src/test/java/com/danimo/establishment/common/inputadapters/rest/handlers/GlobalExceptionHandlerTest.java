package com.danimo.establishment.common.inputadapters.rest.handlers;

import com.danimo.establishment.common.application.exceptions.EstablishmentAlreadyExistException;
import com.danimo.establishment.common.application.exceptions.LocationNotFoundException;
import com.danimo.establishment.common.infrastructure.inputadapters.rest.dto.RestApiError;
import com.danimo.establishment.common.infrastructure.inputadapters.rest.handlers.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void handleEstablishmentAlreadyExistException_returnsConflict() {
        // Arrange
        String nombre = "Establishment already exists";
        EstablishmentAlreadyExistException ex = new EstablishmentAlreadyExistException(nombre);

        // Act
        ResponseEntity<RestApiError> response = handler.handleUserNotFoundException(ex);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CONFLICT.value(), response.getBody().getStatusCode());
        assertTrue(response.getBody().getMessage().contains(nombre));
    }

    @Test
    void handleLocationNotFoundException_returnsNotFound() {
        // Arrange
        String message = "Location not found";
        LocationNotFoundException ex = new LocationNotFoundException(message);

        // Act
        ResponseEntity<RestApiError> response = handler.handleLocationNotFoundException(ex);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatusCode());
        assertEquals(message, response.getBody().getMessage());
    }

    @Test
    void handleIllegalArgumentException_returnsBadRequest() {
        // Arrange
        String message = "Illegal argument";
        IllegalArgumentException ex = new IllegalArgumentException(message);

        // Act
        ResponseEntity<RestApiError> response = handler.handleIllegalArgumentException(ex);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatusCode());
        assertEquals(message, response.getBody().getMessage());
    }
}
