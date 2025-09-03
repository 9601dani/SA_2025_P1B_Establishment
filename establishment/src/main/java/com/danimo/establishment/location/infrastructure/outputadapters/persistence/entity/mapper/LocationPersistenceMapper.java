package com.danimo.establishment.location.infrastructure.outputadapters.persistence.entity.mapper;

import com.danimo.establishment.location.domain.*;
import com.danimo.establishment.location.infrastructure.outputadapters.persistence.entity.LocationDbEntity;
import org.springframework.stereotype.Component;

@Component
public class LocationPersistenceMapper {

    public Location toDomain(LocationDbEntity dbEntity){
        if(dbEntity == null) return null;

        return new Location(
                LocationId.fromUuid(dbEntity.getId()),
                dbEntity.getName(),
                dbEntity.getAddress(),
                dbEntity.getCity(),
                dbEntity.getState(),
                dbEntity.getCountry(),
                dbEntity.getZip(),
                new LocationPhone(dbEntity.getPhone()),
                dbEntity.getCapacity(),
                dbEntity.isAvailable(),
                dbEntity.getDescription(),
                new LocationType(dbEntity.getType()),
                LocationCreatedAt.fromDomain(dbEntity.getCreatedAt())
        );
    }

    public LocationDbEntity toDbEntity(Location location){
        if(location == null){
            return null;
        }

        return new LocationDbEntity(
                location.getId().getId(),
                location.getName(),
                location.getAddress(),
                location.getCity(),
                location.getState(),
                location.getCountry(),
                location.getZip(),
                location.getPhone().getNumber(),
                location.getCapacity(),
                location.isAvailable(),
                location.getDescription(),
                location.getType().getName(),
                location.getCreatedAt().getCreatedAt()
        );
    }
}
