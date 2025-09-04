package com.danimo.establishment.location.application.inputports;

import com.danimo.establishment.location.application.usecases.updatelocation.UpdateLocationDto;
import com.danimo.establishment.location.domain.Location;

public interface UpdatingLocationInputPort {
    Location updateLocation(UpdateLocationDto dto);
}
