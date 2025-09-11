package com.danimo.establishment.opinions.infrastructure.outputadapters.persistence.entity.mapper;

import com.danimo.establishment.opinions.domain.*;
import com.danimo.establishment.opinions.infrastructure.outputadapters.persistence.entity.OpinionDbEntity;
import org.springframework.stereotype.Component;

@Component
public class OpinionPersistenceMapper {

    public Opinion toDomain(OpinionDbEntity dbEntity){
        if(dbEntity == null) return null;

        return new Opinion(
                OpinionId.fromUuid(dbEntity.getId()),
                dbEntity.getLocationId(),
                dbEntity.getUsername(),
                OpinionScore.fromInt(dbEntity.getScore()),
                dbEntity.getOpinionTitle(),
                OpinionComment.fromOpinion(dbEntity.getComment()),
                OpinionCreatedAt.fromDate(dbEntity.getCreatedAt())
        );
    }

    public OpinionDbEntity toDbEntity(Opinion opinion){
        if(opinion == null) return null;

        return new OpinionDbEntity(
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
