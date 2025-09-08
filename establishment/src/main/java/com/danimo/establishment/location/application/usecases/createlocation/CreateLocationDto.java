package com.danimo.establishment.location.application.usecases.createlocation;

import com.danimo.establishment.location.domain.*;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CreateLocationDto {
    private final LocationId locationId;

    private String name;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zip;
    private String phone;
    private Integer capacity;
    private boolean available;
    private String description;
    private String type;
    private String imageUrl;
    public Location toDomain(){
        return new Location(
                LocationId.generate(),
                name,
                address,
                city,
                state,
                country,
                zip,
                new LocationPhone(phone),
                capacity,
                available,
                description,
                new LocationType(type),
                LocationCreatedAt.generate(),
                imageUrl
        );
    }
}
