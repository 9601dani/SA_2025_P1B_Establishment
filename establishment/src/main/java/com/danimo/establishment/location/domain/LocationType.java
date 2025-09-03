package com.danimo.establishment.location.domain;

import lombok.Value;

@Value
public class LocationType {
    private final String name;

    public LocationType(String name) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("El tipo de establecimiento no puede ser nulo");
        }

        if(name.isBlank()){
            throw new IllegalArgumentException("El tipo de establecimiento no puede ser blanco");
        }

        if(!name.equals("HOTEL") && !name.equals("RESTAURANT")){
            throw new IllegalArgumentException("El tipo de establecimiento no es valido");
        }

        this.name = name;
    }
}
