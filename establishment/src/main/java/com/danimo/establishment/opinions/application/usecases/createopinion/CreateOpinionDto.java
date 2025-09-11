package com.danimo.establishment.opinions.application.usecases.createopinion;

import com.danimo.establishment.opinions.domain.*;
import lombok.Value;

import java.util.UUID;

@Value
public class CreateOpinionDto {
    private final UUID locationId;
    private final String user;
    private final int score;
    private final String opinionTitle;
    private final String comment;

    public Opinion toDomain(){
        return new Opinion(
                OpinionId.generate(),
                locationId,
                user,
                OpinionScore.fromInt(score),
                opinionTitle.toUpperCase(),
                OpinionComment.fromOpinion(comment),
                OpinionCreatedAt.generate()
        );
    }
}
