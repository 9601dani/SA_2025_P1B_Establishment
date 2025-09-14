package com.danimo.establishment.location.application.usecases.createlocation;

import com.danimo.establishment.common.application.exceptions.EstablishmentAlreadyExistException;
import com.danimo.establishment.location.application.outputports.persistence.FindingLocationByNameOutputPort;
import com.danimo.establishment.location.application.outputports.persistence.StoringLocationOutputPort;
import com.danimo.establishment.location.domain.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateLocationUseCaseTest {

    private static final String LOCATION_NAME = "Test Location";

    private FindingLocationByNameOutputPort findingLocationPort;
    private StoringLocationOutputPort storingLocationOutputPort;
    private CreateLocationUseCase useCase;

    @BeforeEach
    void setUp() {
        findingLocationPort = mock(FindingLocationByNameOutputPort.class);
        storingLocationOutputPort = mock(StoringLocationOutputPort.class);
        useCase = new CreateLocationUseCase(findingLocationPort, storingLocationOutputPort);
    }

    private CreateLocationDto buildDto() {
        return new CreateLocationDto(
                null, // locationId será generado
                LOCATION_NAME,
                "123 Street",
                "City",
                "State",
                "Country",
                "12345",
                "1234567890",
                100,
                true,
                "Description",
                "HOTEL",
                "image.png"
        );
    }

    private Location buildLocation() {
        return buildDto().toDomain();
    }

    @Test
    void createLocation_successful_whenNameDoesNotExist() {
        // Arrange
        CreateLocationDto dto = buildDto();
        Location location = buildLocation();

        when(findingLocationPort.findByName(LOCATION_NAME)).thenReturn(Optional.empty());
        when(storingLocationOutputPort.save(any(Location.class))).thenReturn(location);

        // Act
        Location result = useCase.createLocation(dto);

        // Assert
        assertNotNull(result);
        assertEquals(LOCATION_NAME, result.getName());
        verify(findingLocationPort).findByName(LOCATION_NAME);
        verify(storingLocationOutputPort).save(any(Location.class));
    }

    @Test
    void createLocation_throwsException_whenNameAlreadyExists() {
        // Arrange
        CreateLocationDto dto = buildDto();
        Location existing = buildLocation();

        when(findingLocationPort.findByName(LOCATION_NAME)).thenReturn(Optional.of(existing));

        // Act & Assert
        EstablishmentAlreadyExistException ex = assertThrows(
                EstablishmentAlreadyExistException.class,
                () -> useCase.createLocation(dto)
        );

        assertTrue(ex.getMessage().contains(LOCATION_NAME));
        verify(findingLocationPort).findByName(LOCATION_NAME);
        verifyNoInteractions(storingLocationOutputPort);
    }
}
