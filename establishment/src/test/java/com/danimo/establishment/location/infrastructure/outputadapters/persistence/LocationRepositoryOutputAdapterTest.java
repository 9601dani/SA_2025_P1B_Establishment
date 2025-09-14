package com.danimo.establishment.location.infrastructure.outputadapters.persistence;

import com.danimo.establishment.location.domain.*;
import com.danimo.establishment.location.infrastructure.outputadapters.persistence.entity.LocationDbEntity;
import com.danimo.establishment.location.infrastructure.outputadapters.persistence.entity.mapper.LocationPersistenceMapper;
import com.danimo.establishment.location.infrastructure.outputadapters.persistence.repository.LocationDbEntityJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationRepositoryOutputAdapterTest {

    private LocationDbEntityJpaRepository jpaRepository;
    private LocationPersistenceMapper mapper;
    private LocationRepositoryOutputAdapter adapter;

    @BeforeEach
    void setUp() {
        jpaRepository = mock(LocationDbEntityJpaRepository.class);
        mapper = new LocationPersistenceMapper();
        adapter = new LocationRepositoryOutputAdapter(jpaRepository, mapper);
    }

    @Test
    void findByName_returnsLocationIfExists() {
        // Arrange
        String name = "Test Location";
        LocationDbEntity dbEntity = new LocationDbEntity(
                UUID.randomUUID(), name, "Address", "City", "State", "Country", "Zip",
                "1234567890", 50, true, "Description", "HOTEL", LocalDateTime.now(), "image.jpg"
        );
        when(jpaRepository.findByName(name)).thenReturn(Optional.of(dbEntity));

        // Act
        Optional<Location> result = adapter.findByName(name);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(name, result.get().getName());
    }

    @Test
    void findByName_returnsEmptyIfNotExists() {
        // Arrange
        when(jpaRepository.findByName("NoExist")).thenReturn(Optional.empty());

        // Act
        Optional<Location> result = adapter.findByName("NoExist");

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void save_persistsLocationAndReturnsDomain() {
        // Arrange
        Location location = buildLocation();
        LocationDbEntity dbEntity = mapper.toDbEntity(location);
        when(jpaRepository.save(any(LocationDbEntity.class))).thenReturn(dbEntity);

        // Act
        Location saved = adapter.save(location);

        // Assert
        assertNotNull(saved);
        assertEquals(location.getName(), saved.getName());
        verify(jpaRepository).save(any(LocationDbEntity.class));
    }

    @Test
    void findAllLocations_returnsListOfLocations() {
        // Arrange
        List<LocationDbEntity> entities = List.of(mapper.toDbEntity(buildLocation()));
        when(jpaRepository.findAll()).thenReturn(entities);

        // Act
        List<Location> locations = adapter.findAllLocations();

        // Assert
        assertNotNull(locations);
        assertEquals(1, locations.size());
    }

    @Test
    void findById_returnsLocationIfExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        LocationDbEntity dbEntity = new LocationDbEntity(
                id, "Name", "Address", "City", "State", "Country", "Zip",
                "1234567890", 50, true, "Description", "HOTEL", LocalDateTime.now(), "image.jpg"
        );
        when(jpaRepository.findById(id)).thenReturn(Optional.of(dbEntity));

        // Act
        Optional<Location> result = adapter.findById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId().getId());
    }

    @Test
    void findById_returnsEmptyIfNotExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(jpaRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<Location> result = adapter.findById(id);

        // Assert
        assertTrue(result.isEmpty());
    }

    // Helper method
    private Location buildLocation() {
        return new Location(
                LocationId.generate(),
                "Name",
                "Address",
                "City",
                "State",
                "Country",
                "Zip",
                new LocationPhone("1234567890"),
                50,
                true,
                "Description",
                new LocationType("HOTEL"),
                LocationCreatedAt.generate(),
                "image.jpg"
        );
    }
}
