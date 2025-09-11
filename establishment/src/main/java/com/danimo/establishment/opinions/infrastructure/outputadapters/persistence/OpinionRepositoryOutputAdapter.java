package com.danimo.establishment.opinions.infrastructure.outputadapters.persistence;

import com.danimo.establishment.common.infrastructure.annotations.PersistenceAdapter;
import com.danimo.establishment.opinions.application.outputports.persistence.FindingOpinionByLocationIdOutputPort;
import com.danimo.establishment.opinions.application.outputports.persistence.StoringOpinionOutputPort;
import com.danimo.establishment.opinions.domain.Opinion;
import com.danimo.establishment.opinions.infrastructure.outputadapters.persistence.entity.OpinionDbEntity;
import com.danimo.establishment.opinions.infrastructure.outputadapters.persistence.entity.mapper.OpinionPersistenceMapper;
import com.danimo.establishment.opinions.infrastructure.outputadapters.persistence.repository.OpinionDbEntityJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@PersistenceAdapter
public class OpinionRepositoryOutputAdapter implements FindingOpinionByLocationIdOutputPort,
        StoringOpinionOutputPort {

    private final OpinionDbEntityJpaRepository opinionDbEntityJpaRepository;
    private final OpinionPersistenceMapper opinionPersistenceMapper;

    @Autowired
    public OpinionRepositoryOutputAdapter(OpinionDbEntityJpaRepository opinionDbEntityJpaRepository, OpinionPersistenceMapper operationPersistenceMapper) {
        this.opinionDbEntityJpaRepository = opinionDbEntityJpaRepository;
        this.opinionPersistenceMapper = operationPersistenceMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Opinion> findingOpinionByLocationId(UUID locationId) {
        return opinionDbEntityJpaRepository.findByLocationId(locationId)
                .stream()
                .map(opinionPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Opinion save(Opinion opinion) {
        OpinionDbEntity objectToDb = opinionDbEntityJpaRepository.save(opinionPersistenceMapper.toDbEntity(opinion));

        return opinionPersistenceMapper.toDomain(objectToDb);
    }
}
