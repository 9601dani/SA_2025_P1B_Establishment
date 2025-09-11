package com.danimo.establishment.location.infrastructure.inputadapters.rest;

import com.danimo.establishment.common.application.exceptions.LocationNotFoundException;
import com.danimo.establishment.common.infrastructure.annotations.WebAdapter;
import com.danimo.establishment.location.application.inputports.CreatingEstablishmentInputPort;
import com.danimo.establishment.location.application.inputports.FindingLocationByIdInputPort;
import com.danimo.establishment.location.application.inputports.ListingAllLocationsInputPort;
import com.danimo.establishment.location.application.inputports.UpdatingLocationInputPort;
import com.danimo.establishment.location.application.usecases.createlocation.CreateLocationDto;
import com.danimo.establishment.location.domain.Location;
import com.danimo.establishment.location.infrastructure.inputadapters.rest.dto.CreatedLocationRequestDto;
import com.danimo.establishment.location.infrastructure.inputadapters.rest.dto.CreatedLocationResponseDto;
import com.danimo.establishment.location.infrastructure.inputadapters.rest.dto.LocationResponse;
import com.danimo.establishment.location.infrastructure.inputadapters.rest.dto.UpdatedLocationRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Locations", description = "Operaciones relacionadas a los establecimientos")
@RestController
@RequestMapping("/v1/locations")
@WebAdapter
public class LocationControllerAdapter {

    private final CreatingEstablishmentInputPort creatingEstablishmentInputPort;
    private final ListingAllLocationsInputPort listingAllLocationsInputPort;
    private final FindingLocationByIdInputPort findingLocationByIdInputPort;
    private final UpdatingLocationInputPort updatingLocationInputPort;

    @Autowired
    public LocationControllerAdapter(CreatingEstablishmentInputPort creatingEstablishmentInputPort,
                                     ListingAllLocationsInputPort listingAllLocationsInputPort,
                                     FindingLocationByIdInputPort findingLocationByIdInputPort,
                                     UpdatingLocationInputPort updatingLocationInputPort) {
        this.creatingEstablishmentInputPort = creatingEstablishmentInputPort;
        this.listingAllLocationsInputPort = listingAllLocationsInputPort;
        this.findingLocationByIdInputPort = findingLocationByIdInputPort;
        this.updatingLocationInputPort = updatingLocationInputPort;
    }

    @Operation(
            summary = "Registrar nuevo establecimiento",
            description = "Devuelve la información del establecimiento correspondiente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Establecimiento creado"),
            @ApiResponse(responseCode = "404", description = "Establecimiento no creado")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<CreatedLocationResponseDto> createLocation(@RequestBody CreatedLocationRequestDto createdLocationRequestDto) {
        CreateLocationDto objectAdaptedFromRestToDomain = createdLocationRequestDto.toDomain();

        Location location = creatingEstablishmentInputPort.createLocation(objectAdaptedFromRestToDomain);

        CreatedLocationResponseDto response = CreatedLocationResponseDto.fromDomain(location);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Obtener los establecimientos",
            description = "Devuelve la información de todos los establecimiento correspondientes."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Establecimientos encontrados"),
            @ApiResponse(responseCode = "404", description = "Establecimientos no encontrados")
    })
    @GetMapping
    @Transactional
    public ResponseEntity<List<LocationResponse>> getAllLocations() {
        List<LocationResponse> locations = listingAllLocationsInputPort.getAllLocations()
                .stream()
                .map(LocationResponse::fromDomain)
                .toList();

        return ResponseEntity.ok(locations);
    }

    @Operation(
            summary = "Verificar existencia del Establecimeinto",
            description = "Devuelve si existe o no."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Establecimiento encontrado"),
            @ApiResponse(responseCode = "404", description = "Establecimiento no encontrado")
    })
    @RequestMapping(method = RequestMethod.HEAD, path = "/check/{id}")
    public ResponseEntity<Void> checkLocationExistence(@PathVariable String id) {
        try {
            findingLocationByIdInputPort.findById(UUID.fromString(id));
            return ResponseEntity.ok().build();
        } catch (LocationNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Busca el establecimiento",
            description = "Devuelve el establecimiento si existe."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Establecimiento encontrado"),
            @ApiResponse(responseCode = "404", description = "Establecimiento no encontrado")
    })
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<LocationResponse> getLocationById(@PathVariable String id) {
       Location location = findingLocationByIdInputPort.findById(UUID.fromString(id));

       return ResponseEntity.ok(LocationResponse.fromDomain(location));
    }

    @Operation(
            summary = "Editar un establecimiento",
            description = "Devuelve el establecimiento actualizado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Establecimiento actualizado"),
            @ApiResponse(responseCode = "404", description = "Establecimiento no actualizado")
    })
    @PutMapping
    @Transactional
    public ResponseEntity<LocationResponse> updateLocation(@RequestBody UpdatedLocationRequestDto dto) {
        Location location = updatingLocationInputPort.updateLocation(dto.toDomain());
        return ResponseEntity.ok(LocationResponse.fromDomain(location));
    }

    @Operation(
            summary = "Obtener los establecimientos para la app del cliente",
            description = "Devuelve la información de todos los establecimiento correspondientes."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Establecimientos encontrados"),
            @ApiResponse(responseCode = "404", description = "Establecimientos no encontrados")
    })
    @GetMapping("/client")
    @Transactional
    public ResponseEntity<List<LocationResponse>> getAllLocationsClient() {
        List<LocationResponse> locations = listingAllLocationsInputPort.getAllLocations()
                .stream()
                .map(LocationResponse::fromDomain)
                .toList();

        return ResponseEntity.ok(locations);
    }

    @Operation(
            summary = "Busca el establecimiento para la app de cliente",
            description = "Devuelve el establecimiento si existe."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Establecimiento encontrado"),
            @ApiResponse(responseCode = "404", description = "Establecimiento no encontrado")
    })
    @GetMapping("/client/{id}")
    @Transactional
    public ResponseEntity<LocationResponse> getLocationByIdClient(@PathVariable String id) {
        Location location = findingLocationByIdInputPort.findById(UUID.fromString(id));

        return ResponseEntity.ok(LocationResponse.fromDomain(location));
    }


}
