package com.danimo.establishment.common.application.exceptions;

public class EstablishmentAlreadyExistException extends RuntimeException {
    public EstablishmentAlreadyExistException(String message) {
        super("El establecimiento con nombre: "+message+" ya existe");
    }
}
