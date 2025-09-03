package com.danimo.establishment.location.application.inputports;

import com.danimo.establishment.location.domain.Location;

import java.util.List;

public interface ListingAllLocationsInputPort {
    List<Location> getAllLocations();
}
