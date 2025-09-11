package com.danimo.establishment.opinions.infrastructure.inputadapters.rest.dto;

import com.danimo.establishment.opinions.application.usecases.createopinion.CreateOpinionDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.util.UUID;

@Value
public class OpinionRequestDto {
    @NotBlank
    private final UUID locationId;
    private final String user;
    @NotBlank
    private final Integer score;
    @NotBlank
    private final String opinionTitle;
    @NotBlank
    private final String comment;

    public CreateOpinionDto toDomain() {
        return new CreateOpinionDto(
                locationId, user, score, opinionTitle, comment
        );
    }
}
