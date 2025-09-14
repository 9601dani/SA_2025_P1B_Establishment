package com.danimo.establishment.location.application.usecases.listlocation;

import com.danimo.establishment.location.application.outputports.persistence.FindingAllLocationsOutputPort;
import com.danimo.establishment.location.domain.Location;
import com.danimo.establishment.location.domain.LocationCreatedAt;
import com.danimo.establishment.location.domain.LocationId;
import com.danimo.establishment.location.domain.LocationPhone;
import com.danimo.establishment.location.domain.LocationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListLocationUseCaseTest {

    private static final UUID LOCATION_ID = UUID.randomUUID();
    private FindingAllLocationsOutputPort findingAllLocationsOutputPort;
    private ListLocationUseCase useCase;

    @BeforeEach
    void setUp() {
        findingAllLocationsOutputPort = mock(FindingAllLocationsOutputPort.class);
        useCase = new ListLocationUseCase(findingAllLocationsOutputPort);
    }

    private Location buildLocation() {
        return new Location(
                LocationId.fromUuid(LOCATION_ID),
                "Test Location",
                "123 Street",
                "City",
                "State",
                "Country",
                "12345",
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
    void getAllLocations_returnsLocations_whenLocationsExist() {
        // Arrange
        List<Location> locations = List.of(buildLocation());
        when(findingAllLocationsOutputPort.findAllLocations()).thenReturn(locations);

        // Act
        List<Location> result = useCase.getAllLocations();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Location", result.get(0).getName());
        verify(findingAllLocationsOutputPort).findAllLocations();
    }

    @Test
    void getAllLocations_returnsEmptyList_whenNoLocationsExist() {
        // Arrange
        when(findingAllLocationsOutputPort.findAllLocations()).thenReturn(List.of());

        // Act
        List<Location> result = useCase.getAllLocations();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(findingAllLocationsOutputPort).findAllLocations();
    }
}
