package com.danimo.establishment.opinions.infrastructure.outputadapters.persistence;

import com.danimo.establishment.opinions.domain.*;
import com.danimo.establishment.opinions.infrastructure.outputadapters.persistence.entity.OpinionDbEntity;
import com.danimo.establishment.opinions.infrastructure.outputadapters.persistence.entity.mapper.OpinionPersistenceMapper;
import com.danimo.establishment.opinions.infrastructure.outputadapters.persistence.repository.OpinionDbEntityJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OpinionRepositoryOutputAdapterTest {

    private OpinionDbEntityJpaRepository jpaRepository;
    private OpinionPersistenceMapper mapper;
    private OpinionRepositoryOutputAdapter adapter;

    @BeforeEach
    void setUp() {
        jpaRepository = mock(OpinionDbEntityJpaRepository.class);
        mapper = mock(OpinionPersistenceMapper.class);
        adapter = new OpinionRepositoryOutputAdapter(jpaRepository, mapper);
    }

    @Test
    void save_returnsSavedOpinion() {
        Opinion opinion = new Opinion(
                OpinionId.generate(),
                UUID.randomUUID(),
                "user1",
                OpinionScore.fromInt(5),
                "TITLE",
                OpinionComment.fromOpinion("Great service"),
                OpinionCreatedAt.generate()
        );

        OpinionDbEntity dbEntity = new OpinionDbEntity(
                opinion.getId().getId(),
                opinion.getLocationId(),
                opinion.getUser(),
                opinion.getScore().getOpinion(),
                opinion.getOpinionTitle(),
                opinion.getComment().getOpinion(),
                opinion.getCreatedAt().getCreatedAt()
        );

        when(mapper.toDbEntity(opinion)).thenReturn(dbEntity);
        when(jpaRepository.save(dbEntity)).thenReturn(dbEntity);
        when(mapper.toDomain(dbEntity)).thenReturn(opinion);

        Opinion result = adapter.save(opinion);

        assertEquals(opinion, result);
        verify(mapper).toDbEntity(opinion);
        verify(jpaRepository).save(dbEntity);
        verify(mapper).toDomain(dbEntity);
    }

    @Test
    void findingOpinionByLocationId_returnsListOfOpinions() {
        UUID locationId = UUID.randomUUID();

        OpinionDbEntity db1 = mock(OpinionDbEntity.class);
        OpinionDbEntity db2 = mock(OpinionDbEntity.class);

        Opinion opinion1 = mock(Opinion.class);
        Opinion opinion2 = mock(Opinion.class);

        when(jpaRepository.findByLocationId(locationId)).thenReturn(List.of(db1, db2));
        when(mapper.toDomain(db1)).thenReturn(opinion1);
        when(mapper.toDomain(db2)).thenReturn(opinion2);

        List<Opinion> opinions = adapter.findingOpinionByLocationId(locationId);

        assertEquals(2, opinions.size());
        assertEquals(opinion1, opinions.get(0));
        assertEquals(opinion2, opinions.get(1));

        verify(jpaRepository).findByLocationId(locationId);
        verify(mapper).toDomain(db1);
        verify(mapper).toDomain(db2);
    }
}
