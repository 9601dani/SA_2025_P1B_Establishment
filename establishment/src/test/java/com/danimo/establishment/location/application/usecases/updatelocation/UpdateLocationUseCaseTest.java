package com.danimo.establishment.location.application.usecases.updatelocation;

import com.danimo.establishment.common.application.exceptions.LocationNotFoundException;
import com.danimo.establishment.location.application.outputports.persistence.FindingLocationByIdOutputPort;
import com.danimo.establishment.location.application.outputports.persistence.StoringLocationOutputPort;
import com.danimo.establishment.location.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateLocationUseCaseTest {

    private static final UUID LOCATION_ID = UUID.randomUUID();
    private FindingLocationByIdOutputPort findingLocationByIdOutputPort;
    private StoringLocationOutputPort storingLocationOutputPort;
    private UpdateLocationUseCase useCase;

    @BeforeEach
    void setUp() {
        findingLocationByIdOutputPort = mock(FindingLocationByIdOutputPort.class);
        storingLocationOutputPort = mock(StoringLocationOutputPort.class);
        useCase = new UpdateLocationUseCase(findingLocationByIdOutputPort, storingLocationOutputPort);
    }

    private Location buildLocation() {
        return new Location(
                LocationId.fromUuid(LOCATION_ID),
                "Old Name",
                "Old Address",
                "Old City",
                "Old State",
                "Old Country",
                "00000",
                new LocationPhone("0000000000"),
                50,
                true,
                "Old Description",
                new LocationType("HOTEL"),
                LocationCreatedAt.generate(),
                "old_image.png"
        );
    }

    private UpdateLocationDto buildDto() {
        return new UpdateLocationDto(
                LocationId.fromUuid(LOCATION_ID),
                "New Name",
                "New Address",
                "New City",
                "New State",
                "New Country",
                "12345",
                new LocationPhone("1234567890"),
                100,
                false,
                "New Description",
                new LocationType("RESTAURANT"),
                LocationCreatedAt.generate(),
                "new_image.png"
        );
    }

    @Test
    void updateLocation_success_whenLocationExists() {
        // Arrange
        Location existing = buildLocation();
        UpdateLocationDto dto = buildDto();

        when(findingLocationByIdOutputPort.findById(LOCATION_ID)).thenReturn(Optional.of(existing));
        when(storingLocationOutputPort.save(existing)).thenReturn(existing);

        // Act
        Location updated = useCase.updateLocation(dto);

        // Assert
        assertNotNull(updated);
        assertEquals("New Name", updated.getName());
        assertEquals("New Address", updated.getAddress());
        assertEquals("New City", updated.getCity());
        assertEquals("New State", updated.getState());
        assertEquals("New Country", updated.getCountry());
        assertEquals("12345", updated.getZip());
        assertEquals("1234567890", updated.getPhone().getNumber());
        assertEquals(100, updated.getCapacity());
        assertFalse(updated.isAvailable());
        assertEquals("New Description", updated.getDescription());
        assertEquals("RESTAURANT", updated.getType().getName());
        assertEquals("new_image.png", updated.getImageUrl());

        verify(findingLocationByIdOutputPort).findById(LOCATION_ID);
        verify(storingLocationOutputPort).save(existing);
    }

    @Test
    void updateLocation_throwsException_whenLocationDoesNotExist() {
        // Arrange
        UpdateLocationDto dto = buildDto();
        when(findingLocationByIdOutputPort.findById(LOCATION_ID)).thenReturn(Optional.empty());

        // Act & Assert
        LocationNotFoundException ex = assertThrows(
                LocationNotFoundException.class,
                () -> useCase.updateLocation(dto)
        );

        assertTrue(ex.getMessage().contains("no encontrada"));
        verify(findingLocationByIdOutputPort).findById(LOCATION_ID);
        verifyNoInteractions(storingLocationOutputPort);
    }
}
