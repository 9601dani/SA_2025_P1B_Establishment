package com.danimo.establishment.location.application.outputports.persistence;

import com.danimo.establishment.location.domain.Location;

import java.util.Optional;
import java.util.UUID;

public interface FindingLocationByIdOutputPort {
    Optional<Location> findById(UUID id);
}
