package com.danimo.establishment.opinions.application.inputports;

import com.danimo.establishment.opinions.domain.Opinion;

import java.util.List;
import java.util.UUID;

public interface FindingOpinionsByLocationIdInputPorts {
    List<Opinion> findingOpinionsByLocationId(UUID locationId);
}
