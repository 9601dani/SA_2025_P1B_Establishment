package com.danimo.establishment.location.domain;

import lombok.Value;

import java.util.regex.Pattern;

@Value
public class LocationPhone {
    private final String number;

    public LocationPhone(String number) {
        if(number == null || number.isEmpty()) {
            throw new IllegalArgumentException("El numero de telefono no puede ser nulo");
        }

        if(!Pattern.matches("[0-9]{10}", number)) {
            throw new IllegalArgumentException("El numero de telefono no es valido");
        }
        this.number = number;
    }
}
