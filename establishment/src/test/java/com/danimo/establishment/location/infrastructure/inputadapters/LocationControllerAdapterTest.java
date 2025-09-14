package com.danimo.establishment.location.infrastructure.inputadapters;

import com.danimo.establishment.common.application.exceptions.LocationNotFoundException;
import com.danimo.establishment.location.application.inputports.CreatingEstablishmentInputPort;
import com.danimo.establishment.location.application.inputports.FindingLocationByIdInputPort;
import com.danimo.establishment.location.application.inputports.ListingAllLocationsInputPort;
import com.danimo.establishment.location.application.inputports.UpdatingLocationInputPort;
import com.danimo.establishment.location.application.usecases.updatelocation.UpdateLocationDto;
import com.danimo.establishment.location.domain.*;
import com.danimo.establishment.location.infrastructure.inputadapters.rest.LocationControllerAdapter;
import com.danimo.establishment.location.infrastructure.inputadapters.rest.dto.CreatedLocationRequestDto;
import com.danimo.establishment.location.infrastructure.inputadapters.rest.dto.UpdatedLocationRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationControllerAdapterTest {

    private CreatingEstablishmentInputPort creatingInputPort;
    private ListingAllLocationsInputPort listingInputPort;
    private FindingLocationByIdInputPort findingInputPort;
    private UpdatingLocationInputPort updatingInputPort;
    private LocationControllerAdapter controller;

    private static final UUID LOCATION_ID = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        creatingInputPort = mock(CreatingEstablishmentInputPort.class);
        listingInputPort = mock(ListingAllLocationsInputPort.class);
        findingInputPort = mock(FindingLocationByIdInputPort.class);
        updatingInputPort = mock(UpdatingLocationInputPort.class);
        controller = new LocationControllerAdapter(creatingInputPort, listingInputPort, findingInputPort, updatingInputPort);
    }

    private Location buildLocation() {
        return new Location(
                LocationId.fromUuid(LOCATION_ID),
                "Test Name",
                "Address",
                "City",
                "State",
                "Country",
                "00000",
                new LocationPhone("1234567890"),
                100,
                true,
                "Description",
                new LocationType("HOTEL"),
                LocationCreatedAt.generate(),
                "image.png"
        );
    }

    @Test
    void createLocation_returnsCreatedResponse() {
        // Arrange
        CreatedLocationRequestDto dto = new CreatedLocationRequestDto(
                "Test Name","Address","City","State","Country","00000","1234567890",100,true,"Description","HOTEL","image.png"
        );
        Location location = buildLocation();
        when(creatingInputPort.createLocation(any())).thenReturn(location);

        // Act
        ResponseEntity<?> response = controller.createLocation(dto);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(creatingInputPort).createLocation(any());
    }

    @Test
    void getAllLocations_returnsListOfLocations() {
        // Arrange
        Location loc = buildLocation();
        when(listingInputPort.getAllLocations()).thenReturn(List.of(loc));

        // Act
        ResponseEntity<?> response = controller.getAllLocations();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, ((List<?>)response.getBody()).size());
    }

    @Test
    void checkLocationExistence_returnsOkIfExists() throws LocationNotFoundException {
        // Arrange
        when(findingInputPort.findById(LOCATION_ID)).thenReturn(buildLocation());

        // Act
        ResponseEntity<Void> response = controller.checkLocationExistence(LOCATION_ID.toString());

        // Assert
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void checkLocationExistence_returnsNotFoundIfDoesNotExist() throws LocationNotFoundException {
        // Arrange
        when(findingInputPort.findById(LOCATION_ID)).thenThrow(new LocationNotFoundException("Not found"));

        // Act
        ResponseEntity<Void> response = controller.checkLocationExistence(LOCATION_ID.toString());

        // Assert
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void getLocationById_returnsLocation() throws LocationNotFoundException {
        // Arrange
        Location loc = buildLocation();
        when(findingInputPort.findById(LOCATION_ID)).thenReturn(loc);

        // Act
        ResponseEntity<?> response = controller.getLocationById(LOCATION_ID.toString());

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void updateLocation_returnsUpdatedLocation() {
        // Arrange
        UpdatedLocationRequestDto dto = mock(UpdatedLocationRequestDto.class);
        UpdateLocationDto domainDto = mock(UpdateLocationDto.class); // Mock del domain DTO
        Location loc = buildLocation();

        when(dto.toDomain()).thenReturn(domainDto);
        when(updatingInputPort.updateLocation(domainDto)).thenReturn(loc);

        // Act
        ResponseEntity<?> response = controller.updateLocation(dto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(updatingInputPort).updateLocation(domainDto);
    }

    @Test
    void getAllLocationsClient_returnsListOfLocations() {
        // Arrange
        Location loc = buildLocation();
        when(listingInputPort.getAllLocations()).thenReturn(List.of(loc));

        // Act
        ResponseEntity<?> response = controller.getAllLocationsClient();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, ((List<?>)response.getBody()).size());
    }

    @Test
    void getLocationByIdClient_returnsLocation() throws LocationNotFoundException {
        // Arrange
        Location loc = buildLocation();
        when(findingInputPort.findById(LOCATION_ID)).thenReturn(loc);

        // Act
        ResponseEntity<?> response = controller.getLocationByIdClient(LOCATION_ID.toString());

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
}
