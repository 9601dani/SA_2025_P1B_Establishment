package com.danimo.establishment.opinions.application.usecases.createopinion;


import com.danimo.establishment.opinions.domain.Opinion;
import com.danimo.establishment.opinions.application.outputports.persistence.StoringOpinionOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CreateOpinionUseCaseTest {

    private StoringOpinionOutputPort storingOpinionOutputPort;
    private CreateOpinionUseCase createOpinionUseCase;

    @BeforeEach
    void setUp() {
        storingOpinionOutputPort = mock(StoringOpinionOutputPort.class);
        createOpinionUseCase = new CreateOpinionUseCase(storingOpinionOutputPort);
    }

    @Test
    void createOpinion_savesAndReturnsOpinion() {
        UUID locationId = UUID.randomUUID();
        CreateOpinionDto dto = new CreateOpinionDto(
                locationId,
                "user123",
                5,
                "Great Service",
                "Really enjoyed my visit"
        );

        Opinion opinionFromDto = dto.toDomain();

        when(storingOpinionOutputPort.save(any(Opinion.class))).thenReturn(opinionFromDto);

        Opinion result = createOpinionUseCase.createOpinion(dto);

        assertEquals(opinionFromDto, result);

        verify(storingOpinionOutputPort, times(1)).save(any(Opinion.class));
    }
}
