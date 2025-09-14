package com.danimo.establishment.opinions.infrastructure.inputadapters.rest;

import com.danimo.establishment.opinions.application.inputports.CreateOpinionInputPort;
import com.danimo.establishment.opinions.application.outputports.persistence.FindingOpinionByLocationIdOutputPort;
import com.danimo.establishment.opinions.domain.*;
import com.danimo.establishment.opinions.infrastructure.inputadapters.rest.dto.OpinionRequestDto;
import com.danimo.establishment.opinions.infrastructure.inputadapters.rest.dto.OpinionResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OpinionControllerAdapterTest {

    private CreateOpinionInputPort createOpinionInputPort;
    private FindingOpinionByLocationIdOutputPort findingOpinionByLocationIdOutputPort;
    private OpinionControllerAdapter controller;

    @BeforeEach
    void setUp() {
        createOpinionInputPort = mock(CreateOpinionInputPort.class);
        findingOpinionByLocationIdOutputPort = mock(FindingOpinionByLocationIdOutputPort.class);
        controller = new OpinionControllerAdapter(createOpinionInputPort, findingOpinionByLocationIdOutputPort);
    }

    @Test
    void createOpinion_returnsCreatedOpinion() {
        UUID locationId = UUID.randomUUID();

        OpinionRequestDto dto = new OpinionRequestDto(locationId, "user1", 5, "Great", "Excellent service!");

        Opinion opinion = new Opinion(
                OpinionId.generate(),
                locationId,
                "user1",
                OpinionScore.fromInt(5),
                "GREAT",
                OpinionComment.fromOpinion("Excellent service!"),
                OpinionCreatedAt.generate()
        );

        when(createOpinionInputPort.createOpinion(any())).thenReturn(opinion);

        ResponseEntity<OpinionResponseDto> response = controller.createOpinion(dto);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(opinion.getId().getId(), response.getBody().getId());
        assertEquals(opinion.getOpinionTitle(), response.getBody().getOpinionTitle());

        verify(createOpinionInputPort, times(1)).createOpinion(any());
    }

    @Test
    void getOpinionsByLocationId_returnsListOfOpinions() {
        UUID locationId = UUID.randomUUID();

        Opinion opinion1 = new Opinion(
                OpinionId.generate(),
                locationId,
                "user1",
                OpinionScore.fromInt(5),
                "TITLE1",
                OpinionComment.fromOpinion("Good!"),
                OpinionCreatedAt.generate()
        );

        Opinion opinion2 = new Opinion(
                OpinionId.generate(),
                locationId,
                "user2",
                OpinionScore.fromInt(4),
                "TITLE2",
                OpinionComment.fromOpinion("Nice!"),
                OpinionCreatedAt.generate()
        );

        when(findingOpinionByLocationIdOutputPort.findingOpinionByLocationId(locationId))
                .thenReturn(List.of(opinion1, opinion2));

        ResponseEntity<List<OpinionResponseDto>> response = controller.getOPinionsByLocationId(locationId.toString());

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        assertEquals(opinion1.getOpinionTitle(), response.getBody().get(0).getOpinionTitle());
        assertEquals(opinion2.getOpinionTitle(), response.getBody().get(1).getOpinionTitle());

        verify(findingOpinionByLocationIdOutputPort, times(1))
                .findingOpinionByLocationId(locationId);
    }
}
