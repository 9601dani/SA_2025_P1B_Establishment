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

        String regex = "^(?:\\+?\\d{10,15}|\\+?\\d{1,3}?[- .]?\\d{2,4}[- .]?\\d{2,4}[- .]?\\d{2,4})$";

        if (!Pattern.matches(regex, number)) {
            throw new IllegalArgumentException("El número de teléfono no es válido");
        }
        this.number = number;
    }
}
