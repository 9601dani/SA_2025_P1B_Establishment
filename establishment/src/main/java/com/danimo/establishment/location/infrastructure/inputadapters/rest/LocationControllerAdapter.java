package com.danimo.establishment.location.infrastructure.inputadapters.rest;

import com.danimo.establishment.common.infrastructure.annotations.WebAdapter;
import com.danimo.establishment.location.application.inputports.CreatingEstablishmentInputPort;
import com.danimo.establishment.location.application.inputports.ListingAllLocationsInputPort;
import com.danimo.establishment.location.application.usecases.createlocation.CreateLocationDto;
import com.danimo.establishment.location.domain.Location;
import com.danimo.establishment.location.infrastructure.inputadapters.rest.dto.CreatedLocationRequestDto;
import com.danimo.establishment.location.infrastructure.inputadapters.rest.dto.CreatedLocationResponseDto;
import com.danimo.establishment.location.infrastructure.inputadapters.rest.dto.LocationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/locations")
@WebAdapter
public class LocationControllerAdapter {

    private final CreatingEstablishmentInputPort creatingEstablishmentInputPort;
    private final ListingAllLocationsInputPort listingAllLocationsInputPort;

    @Autowired
    public LocationControllerAdapter(CreatingEstablishmentInputPort creatingEstablishmentInputPort,
                                     ListingAllLocationsInputPort listingAllLocationsInputPort) {
        this.creatingEstablishmentInputPort = creatingEstablishmentInputPort;
        this.listingAllLocationsInputPort = listingAllLocationsInputPort;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CreatedLocationResponseDto> createLocation(@RequestBody CreatedLocationRequestDto createdLocationRequestDto) {
        CreateLocationDto objectAdaptedFromRestToDomain = createdLocationRequestDto.toDomain();

        Location location = creatingEstablishmentInputPort.createLocation(objectAdaptedFromRestToDomain);

        CreatedLocationResponseDto response = CreatedLocationResponseDto.fromDomain(location);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<List<LocationResponse>> getAllLocations() {
        List<LocationResponse> locations = listingAllLocationsInputPort.getAllLocations()
                .stream()
                .map(LocationResponse::fromDomain)
                .toList();

        return ResponseEntity.ok(locations);
    }



}
