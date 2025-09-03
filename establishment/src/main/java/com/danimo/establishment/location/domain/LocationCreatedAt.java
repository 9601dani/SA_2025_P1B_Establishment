package com.danimo.establishment.location.domain;

import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class LocationCreatedAt {
    private final LocalDateTime createdAt;

    private LocationCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public static LocationCreatedAt generate() {
        return new LocationCreatedAt(LocalDateTime.now());
    }

    public static LocationCreatedAt fromDomain(LocalDateTime createdAt) {
        return new LocationCreatedAt(createdAt);
    }

}
