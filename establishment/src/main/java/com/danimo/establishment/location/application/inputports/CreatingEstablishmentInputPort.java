package com.danimo.establishment.location.application.inputports;

import com.danimo.establishment.location.application.usecases.createlocation.CreateLocationDto;
import com.danimo.establishment.location.domain.Location;
import jakarta.validation.Valid;

public interface CreatingEstablishmentInputPort {
    Location createLocation (@Valid CreateLocationDto locationDto);
}
