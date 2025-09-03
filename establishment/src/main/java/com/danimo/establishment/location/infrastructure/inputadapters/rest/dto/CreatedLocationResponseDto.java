package com.danimo.establishment.location.infrastructure.inputadapters.rest.dto;

import com.danimo.establishment.location.domain.Location;
import com.danimo.establishment.location.domain.LocationId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class CreatedLocationResponseDto {
    private String id;
    private String name;
    private String type;

    public static CreatedLocationResponseDto fromDomain(Location location) {
        return new CreatedLocationResponseDto(location.getId().getId().toString(), location.getName(), location.getType().getName());
    }
}
