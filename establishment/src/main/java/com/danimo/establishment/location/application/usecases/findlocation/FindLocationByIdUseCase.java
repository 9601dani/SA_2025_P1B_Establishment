package com.danimo.establishment.location.application.usecases.findlocation;

import com.danimo.establishment.common.application.annotations.UseCase;
import com.danimo.establishment.common.application.exceptions.LocationNotFoundException;
import com.danimo.establishment.location.application.inputports.FindingLocationByIdInputPort;
import com.danimo.establishment.location.application.outputports.persistence.FindingLocationByIdOutputPort;
import com.danimo.establishment.location.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@UseCase
public class FindLocationByIdUseCase implements FindingLocationByIdInputPort {

    private final FindingLocationByIdOutputPort findingLocationByIdOutputPort;

    @Autowired
    FindLocationByIdUseCase(FindingLocationByIdOutputPort findingLocationByIdOutputPort) {
        this.findingLocationByIdOutputPort = findingLocationByIdOutputPort;
    }

    @Override
    public Location findById(UUID locationId) throws LocationNotFoundException {
        return findingLocationByIdOutputPort.findById(locationId)
                .orElseThrow(() -> new LocationNotFoundException("El Establecimiento no existe"));
    }
}
