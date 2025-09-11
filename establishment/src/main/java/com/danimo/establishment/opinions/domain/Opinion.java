package com.danimo.establishment.opinions.domain;

import com.danimo.establishment.common.domain.annotations.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@DomainEntity
@AllArgsConstructor
public class Opinion {
    private final OpinionId id;
    private final UUID locationId;
    private final String user;
    private final OpinionScore score;
    private final String opinionTitle;
    private final OpinionComment comment;
    private final OpinionCreatedAt createdAt;


}
