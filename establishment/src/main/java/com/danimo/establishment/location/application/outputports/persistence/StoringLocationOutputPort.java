package com.danimo.establishment.location.application.outputports.persistence;

import com.danimo.establishment.location.domain.Location;


public interface StoringLocationOutputPort {
    Location save(Location location);
}
