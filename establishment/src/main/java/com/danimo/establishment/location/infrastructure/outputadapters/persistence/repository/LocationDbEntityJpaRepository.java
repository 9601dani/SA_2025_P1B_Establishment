package com.danimo.establishment.location.infrastructure.outputadapters.persistence.repository;

import com.danimo.establishment.location.infrastructure.outputadapters.persistence.entity.LocationDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocationDbEntityJpaRepository extends JpaRepository<LocationDbEntity, UUID> {
    Optional<LocationDbEntity> findByName(String locationName);
}
