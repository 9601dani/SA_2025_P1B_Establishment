package com.danimo.establishment.location.infrastructure.inputadapters.rest.dto;

import com.danimo.establishment.location.application.usecases.createlocation.CreateLocationDto;
import com.danimo.establishment.location.application.usecases.updatelocation.UpdateLocationDto;
import com.danimo.establishment.location.domain.LocationCreatedAt;
import com.danimo.establishment.location.domain.LocationId;
import com.danimo.establishment.location.domain.LocationPhone;
import com.danimo.establishment.location.domain.LocationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class UpdatedLocationRequestDto {
    @NotBlank
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    @NotBlank
    private String country;
    @NotBlank
    private String zip;
    @NotBlank
    private String phone;
    @NotNull
    private Integer capacity;
    private boolean available;
    @NotBlank
    private String description;
    @NotBlank
    private String type;
    @NotBlank
    private LocalDateTime createdAt;

    public UpdateLocationDto toDomain(){
        return new UpdateLocationDto(
                new LocationId(UUID.fromString(id)),
                name,
                address,
                city,
                state,
                country,
                zip,
                new LocationPhone(phone),
                capacity,
                available,
                description,
                new LocationType(type),
                new LocationCreatedAt(createdAt)

        );
    }

}
