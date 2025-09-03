package com.danimo.establishment.location.infrastructure.outputadapters.persistence;

import com.danimo.establishment.common.infrastructure.annotations.PersistenceAdapter;
import com.danimo.establishment.location.application.inputports.ListingAllLocationsInputPort;
import com.danimo.establishment.location.application.outputports.persistence.FindingAllLocationsOutputPort;
import com.danimo.establishment.location.application.outputports.persistence.FindingLocationByNameOutputPort;
import com.danimo.establishment.location.application.outputports.persistence.StoringLocationOutputPort;
import com.danimo.establishment.location.domain.Location;
import com.danimo.establishment.location.infrastructure.outputadapters.persistence.entity.LocationDbEntity;
import com.danimo.establishment.location.infrastructure.outputadapters.persistence.entity.mapper.LocationPersistenceMapper;
import com.danimo.establishment.location.infrastructure.outputadapters.persistence.repository.LocationDbEntityJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
public class LocationRepositoryOutputAdapter implements FindingLocationByNameOutputPort,
        StoringLocationOutputPort, FindingAllLocationsOutputPort {

    private final LocationDbEntityJpaRepository locationDbEntityJpaRepository;
    private final LocationPersistenceMapper locationPersistenceMapper;

    @Autowired
    public LocationRepositoryOutputAdapter(LocationDbEntityJpaRepository locationDbEntityJpaRepository,
                                           LocationPersistenceMapper locationPersistenceMapper) {
        this.locationDbEntityJpaRepository = locationDbEntityJpaRepository;
        this.locationPersistenceMapper = locationPersistenceMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Location> findByName(String locationName) {
       return locationDbEntityJpaRepository.findByName(locationName)
               .map(locationPersistenceMapper::toDomain);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Location save(Location location) {
        LocationDbEntity savedLocation = locationDbEntityJpaRepository.save(locationPersistenceMapper
                .toDbEntity(location));

        return locationPersistenceMapper.toDomain(savedLocation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Location> findAllLocations() {
        return locationDbEntityJpaRepository.findAll()
                .stream()
                .map(locationPersistenceMapper::toDomain)
                .toList();
    }
}
