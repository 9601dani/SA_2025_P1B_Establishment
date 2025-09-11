package com.danimo.establishment.opinions.application.usecases.createopinion;

import com.danimo.establishment.common.application.annotations.UseCase;
import com.danimo.establishment.opinions.application.inputports.CreateOpinionInputPort;
import com.danimo.establishment.opinions.application.outputports.persistence.FindingOpinionByLocationIdOutputPort;
import com.danimo.establishment.opinions.application.outputports.persistence.StoringOpinionOutputPort;
import com.danimo.establishment.opinions.domain.Opinion;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
public class CreateOpinionUseCase implements CreateOpinionInputPort {
    private final StoringOpinionOutputPort storingOpinionOutputPort;

    @Autowired
    public CreateOpinionUseCase(StoringOpinionOutputPort storingOpinionOutputPort) {
        this.storingOpinionOutputPort = storingOpinionOutputPort;
    }


    @Override
    public Opinion createOpinion(CreateOpinionDto dto) {
        Opinion newOpinion = dto.toDomain();
        return storingOpinionOutputPort.save(newOpinion);
    }
}
