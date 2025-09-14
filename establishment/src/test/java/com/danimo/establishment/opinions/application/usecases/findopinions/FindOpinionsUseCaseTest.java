package com.danimo.establishment.opinions.application.usecases.findopinions;

import com.danimo.establishment.opinions.application.outputports.persistence.FindingOpinionByLocationIdOutputPort;
import com.danimo.establishment.opinions.domain.Opinion;
import com.danimo.establishment.opinions.domain.OpinionId;
import com.danimo.establishment.opinions.domain.OpinionScore;
import com.danimo.establishment.opinions.domain.OpinionComment;
import com.danimo.establishment.opinions.domain.OpinionCreatedAt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FindOpinionsUseCaseTest {

    private FindingOpinionByLocationIdOutputPort findingOpinionByLocationIdOutputPort;
    private FindOpinionsUseCase findOpinionsUseCase;

    @BeforeEach
    void setUp() {
        findingOpinionByLocationIdOutputPort = mock(FindingOpinionByLocationIdOutputPort.class);
        findOpinionsUseCase = new FindOpinionsUseCase(findingOpinionByLocationIdOutputPort);
    }

    @Test
    void findingOpinionsByLocationId_returnsListOfOpinions() {
        UUID locationId = UUID.randomUUID();

        Opinion opinion1 = new Opinion(
                OpinionId.generate(),
                locationId,
                "user1",
                OpinionScore.fromInt(5),
                "TITLE1",
                OpinionComment.fromOpinion("Great!"),
                OpinionCreatedAt.generate()
        );

        Opinion opinion2 = new Opinion(
                OpinionId.generate(),
                locationId,
                "user2",
                OpinionScore.fromInt(4),
                "TITLE2",
                OpinionComment.fromOpinion("Good!"),
                OpinionCreatedAt.generate()
        );

        List<Opinion> expectedOpinions = List.of(opinion1, opinion2);

        when(findingOpinionByLocationIdOutputPort.findingOpinionByLocationId(any(UUID.class)))
                .thenReturn(expectedOpinions);

        List<Opinion> result = findOpinionsUseCase.findingOpinionsByLocationId(locationId);

        assertEquals(expectedOpinions, result);

        verify(findingOpinionByLocationIdOutputPort, times(1))
                .findingOpinionByLocationId(any(UUID.class));
    }
}
