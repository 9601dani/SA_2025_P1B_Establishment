package com.danimo.establishment.location.application.outputports.persistence;

import com.danimo.establishment.location.domain.Location;

import java.util.List;

public interface FindingAllLocationsOutputPort {
    List<Location> findAllLocations();
}
