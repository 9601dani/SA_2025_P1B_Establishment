package com.danimo.establishment.opinions.application.outputports.persistence;

import com.danimo.establishment.opinions.domain.Opinion;

public interface StoringOpinionOutputPort {
    Opinion save(Opinion opinion);
}
