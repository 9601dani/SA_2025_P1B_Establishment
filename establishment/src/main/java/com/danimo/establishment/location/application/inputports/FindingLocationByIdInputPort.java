package com.danimo.establishment.location.application.inputports;

import com.danimo.establishment.common.application.exceptions.LocationNotFoundException;
import com.danimo.establishment.location.domain.Location;

import java.util.UUID;

public interface FindingLocationByIdInputPort {
    Location findById(UUID locationId) throws LocationNotFoundException;
}
