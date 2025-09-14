package com.danimo.establishment.location.application.usecases.findlocation;

import com.danimo.establishment.common.application.exceptions.LocationNotFoundException;
import com.danimo.establishment.location.application.outputports.persistence.FindingLocationByIdOutputPort;
import com.danimo.establishment.location.domain.Location;
import com.danimo.establishment.location.domain.LocationCreatedAt;
import com.danimo.establishment.location.domain.LocationId;
import com.danimo.establishment.location.domain.LocationPhone;
import com.danimo.establishment.location.domain.LocationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindLocationByIdUseCaseTest {

    private static final UUID LOCATION_ID = UUID.randomUUID();
    private FindingLocationByIdOutputPort findingLocationByIdOutputPort;
    private FindLocationByIdUseCase useCase;

    @BeforeEach
    void setUp() {
        findingLocationByIdOutputPort = mock(FindingLocationByIdOutputPort.class);
        useCase = new FindLocationByIdUseCase(findingLocationByIdOutputPort);
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
    void findById_success_whenLocationExists() throws LocationNotFoundException {
        // Arrange
        Location location = buildLocation();
        when(findingLocationByIdOutputPort.findById(LOCATION_ID)).thenReturn(Optional.of(location));

        // Act
        Location result = useCase.findById(LOCATION_ID);

        // Assert
        assertNotNull(result);
        assertEquals(LOCATION_ID, result.getId().getId());
        assertEquals("Test Location", result.getName());
        verify(findingLocationByIdOutputPort).findById(LOCATION_ID);
    }

    @Test
    void findById_throwsException_whenLocationDoesNotExist() {
        // Arrange
        when(findingLocationByIdOutputPort.findById(LOCATION_ID)).thenReturn(Optional.empty());

        // Act & Assert
        LocationNotFoundException ex = assertThrows(
                LocationNotFoundException.class,
                () -> useCase.findById(LOCATION_ID)
        );

        assertTrue(ex.getMessage().contains("no existe") || ex.getMessage().contains("no encontrado"));
        verify(findingLocationByIdOutputPort).findById(LOCATION_ID);
    }
}
