package com.danimo.establishment.location.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class LocationId {
    private final UUID id;

    private LocationId(UUID id) {
        this.id = id;
    }

    public static LocationId generate() {
        return new LocationId(UUID.randomUUID());
    }

    public static LocationId fromUuid(UUID uuid) {
        return new LocationId(uuid);
    }
}
