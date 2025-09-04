package com.danimo.establishment.location.application.usecases.updatelocation;

import com.danimo.establishment.common.application.annotations.UseCase;
import com.danimo.establishment.common.application.exceptions.LocationNotFoundException;
import com.danimo.establishment.location.application.inputports.UpdatingLocationInputPort;
import com.danimo.establishment.location.application.outputports.persistence.FindingLocationByIdOutputPort;
import com.danimo.establishment.location.application.outputports.persistence.StoringLocationOutputPort;
import com.danimo.establishment.location.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
public class UpdateLocationUseCase implements UpdatingLocationInputPort {

    private final FindingLocationByIdOutputPort findingLocationByIdOutputPort;
    private final StoringLocationOutputPort storingLocationOutputPort;

    @Autowired
    public UpdateLocationUseCase(FindingLocationByIdOutputPort findingLocationByIdOutputPort,
                                 StoringLocationOutputPort storingLocationOutputPort) {
        this.findingLocationByIdOutputPort = findingLocationByIdOutputPort;
        this.storingLocationOutputPort = storingLocationOutputPort;
    }

    @Override
    public Location updateLocation(UpdateLocationDto dto) {
        Location location = this.findingLocationByIdOutputPort
                .findById(dto.getId().getId())
                .orElseThrow(() -> new LocationNotFoundException("Location no encontrada"));

        location.setName(dto.getName());
        location.setAddress(dto.getAddress());
        location.setCity(dto.getCity());
        location.setState(dto.getState());
        location.setCountry(dto.getCountry());
        location.setZip(dto.getZip());
        location.setPhone(dto.getPhone());
        location.setCapacity(dto.getCapacity());
        location.setAvailable(dto.isAvailable());
        location.setDescription(dto.getDescription());
        location.setType(dto.getType());
        location.setCreatedAt(dto.getCreatedAt());

        return this.storingLocationOutputPort.save(location);
    }
}
