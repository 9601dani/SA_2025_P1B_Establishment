package com.danimo.establishment.location.infrastructure.inputadapters.rest.dto;

import com.danimo.establishment.location.domain.Location;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class LocationResponse {
    private final String id;
    private final String name;
    private final String address;
    private final String city;
    private final String state;
    private final String country;
    private final String zip;
    private final String phone;
    private final Integer capacity;
    private final boolean available;
    private final String description;
    private final String type;
    private final LocalDateTime createdAt;
    private final String imageUrl;

    public static LocationResponse fromDomain(Location location) {
        return new LocationResponse(
                location.getId().getId().toString(),
                location.getName(),
                location.getAddress(),
                location.getCity(),
                location.getState(),
                location.getCountry(),
                location.getZip(),
                location.getPhone().getNumber(),
                location.getCapacity(),
                location.isAvailable(),
                location.getDescription(),
                location.getType().getName(),
                location.getCreatedAt().getCreatedAt(),
                location.getImageUrl()
        );
    }
}
