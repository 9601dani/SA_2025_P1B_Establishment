package com.danimo.establishment.location.application.outputports.persistence;

import com.danimo.establishment.location.domain.Location;

import java.util.Optional;

public interface StoringLocationOutputPort {
    Location save(Location location);
}
