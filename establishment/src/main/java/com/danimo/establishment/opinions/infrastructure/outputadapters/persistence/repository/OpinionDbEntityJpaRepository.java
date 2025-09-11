package com.danimo.establishment.opinions.infrastructure.outputadapters.persistence.repository;

import com.danimo.establishment.opinions.infrastructure.outputadapters.persistence.entity.OpinionDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OpinionDbEntityJpaRepository extends JpaRepository<OpinionDbEntity, UUID> {
    List<OpinionDbEntity> findByLocationId(UUID locationId);
}
