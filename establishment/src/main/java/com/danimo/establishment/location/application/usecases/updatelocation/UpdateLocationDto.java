package com.danimo.establishment.location.application.usecases.updatelocation;

import com.danimo.establishment.location.domain.*;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UpdateLocationDto {
    private LocationId id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zip;
    private LocationPhone phone;
    private Integer capacity;
    private boolean available;
    private String description;
    private LocationType type;
    private LocationCreatedAt createdAt;

    public Location toDomain(){
        return new Location(
                id,
                name,
                address,
                city,
                state,
                country,
                zip,
                phone,
                capacity,
                available,
                description,
                type,
                createdAt
        );
    }
}
