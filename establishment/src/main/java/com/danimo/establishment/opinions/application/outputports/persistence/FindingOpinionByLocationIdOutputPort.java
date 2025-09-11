package com.danimo.establishment.opinions.application.outputports.persistence;

import com.danimo.establishment.opinions.domain.Opinion;

import java.util.List;
import java.util.UUID;

public interface FindingOpinionByLocationIdOutputPort {
    List<Opinion> findingOpinionByLocationId(UUID locationId);
}
