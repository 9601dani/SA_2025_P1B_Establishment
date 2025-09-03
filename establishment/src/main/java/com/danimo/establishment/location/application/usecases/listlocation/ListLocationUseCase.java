package com.danimo.establishment.location.application.usecases.listlocation;

import com.danimo.establishment.common.application.annotations.UseCase;
import com.danimo.establishment.location.application.inputports.ListingAllLocationsInputPort;
import com.danimo.establishment.location.application.outputports.persistence.FindingAllLocationsOutputPort;
import com.danimo.establishment.location.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UseCase
public class ListLocationUseCase implements ListingAllLocationsInputPort {

    private final FindingAllLocationsOutputPort findingAllLocationsport;

    @Autowired
    public ListLocationUseCase(FindingAllLocationsOutputPort findingAllLocationsport) {
        this.findingAllLocationsport = findingAllLocationsport;
    }

    @Override
    public List<Location> getAllLocations() {
        return findingAllLocationsport.findAllLocations();
    }
}
