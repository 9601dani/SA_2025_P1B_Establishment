package com.danimo.establishment.opinions.application.inputports;

import com.danimo.establishment.opinions.application.usecases.createopinion.CreateOpinionDto;
import com.danimo.establishment.opinions.domain.Opinion;

public interface CreateOpinionInputPort {
    Opinion createOpinion(CreateOpinionDto dto);
}
