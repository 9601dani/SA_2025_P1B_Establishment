package com.danimo.establishment.opinions.application.usecases.findopinions;

import com.danimo.establishment.common.application.annotations.UseCase;
import com.danimo.establishment.opinions.application.inputports.FindingOpinionsByLocationIdInputPorts;
import com.danimo.establishment.opinions.application.outputports.persistence.FindingOpinionByLocationIdOutputPort;
import com.danimo.establishment.opinions.domain.Opinion;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@UseCase
public class FindOpinionsUseCase implements FindingOpinionsByLocationIdInputPorts {
    private final FindingOpinionByLocationIdOutputPort findingOpinionByLocationIdOutputPort;

    @Autowired
    public FindOpinionsUseCase(FindingOpinionByLocationIdOutputPort findingOpinionByLocationIdOutputPort) {
        this.findingOpinionByLocationIdOutputPort = findingOpinionByLocationIdOutputPort;
    }

    @Override
    public List<Opinion> findingOpinionsByLocationId(UUID locationId) {
        return findingOpinionByLocationIdOutputPort.findingOpinionByLocationId(locationId);
    }
}
