package com.danimo.establishment.opinions.infrastructure.inputadapters.rest.dto;

import com.danimo.establishment.opinions.domain.Opinion;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class OpinionResponseDto {
    private final UUID id;
    private final UUID locationId;
    private final String username;
    private final Integer score;
    private final String opinionTitle;
    private final String comment;
    private final LocalDateTime createdAt;

    public static OpinionResponseDto fromDomain(Opinion opinion) {
        return new OpinionResponseDto(
                opinion.getId().getId(),
                opinion.getLocationId(),
                opinion.getUser(),
                opinion.getScore().getOpinion(),
                opinion.getOpinionTitle(),
                opinion.getComment().getOpinion(),
                opinion.getCreatedAt().getCreatedAt()
        );
    }
}
