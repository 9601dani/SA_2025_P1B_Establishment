package com.danimo.establishment.location.application.usecases.createlocation;

import com.danimo.establishment.common.application.annotations.UseCase;
import com.danimo.establishment.common.application.exceptions.EstablishmentAlreadyExistException;
import com.danimo.establishment.location.application.inputports.CreatingEstablishmentInputPort;
import com.danimo.establishment.location.application.outputports.persistence.FindingLocationByNameOutputPort;
import com.danimo.establishment.location.application.outputports.persistence.StoringLocationOutputPort;
import com.danimo.establishment.location.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

@UseCase
@Validated
public class CreateLocationUseCase implements CreatingEstablishmentInputPort {
    private final FindingLocationByNameOutputPort findingLocationPort;
    private final StoringLocationOutputPort storingLocationOutputPort;

    @Autowired

    public CreateLocationUseCase(FindingLocationByNameOutputPort findingLocationPort,
                                 StoringLocationOutputPort storingLocationOutputPort) {
        this.findingLocationPort = findingLocationPort;
        this.storingLocationOutputPort = storingLocationOutputPort;
    }

    @Override
    public Location createLocation(CreateLocationDto locationDto) {
        if(findingLocationPort.findByName(locationDto.getName()).isPresent()) {
            throw new EstablishmentAlreadyExistException(locationDto.getName());
        }

        Location newLocation = locationDto.toDomain();

        return storingLocationOutputPort.save(newLocation);
    }
}
