package com.danimo.establishment.location.infrastructure.inputadapters.rest.dto;

import com.danimo.establishment.location.application.usecases.createlocation.CreateLocationDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class CreatedLocationRequestDto {
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
    private String imageUrl;

    public CreateLocationDto toDomain(){
        return new CreateLocationDto(
                null,
                name,
                address,
                city,
                state,
                country,
                zip,
                phone,
                capacity,
                available,
                description,
                type,
                imageUrl
        );
    }
}
