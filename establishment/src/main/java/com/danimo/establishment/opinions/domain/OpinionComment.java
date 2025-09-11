package com.danimo.establishment.opinions.domain;

import lombok.Value;

@Value
public class OpinionComment {
    private String opinion;

    public OpinionComment(String opinion) {
        if(opinion == null || opinion.isEmpty()) {
            throw new IllegalArgumentException("La opinion no puede ser nula");
        }

        if(opinion.length()>100){
            throw new IllegalArgumentException("La opinion no puede ser mayor que 100 caracteres");
        }
        this.opinion = opinion;
    }

    public static OpinionComment fromOpinion(String opinion) {
        return new OpinionComment(opinion);
    }
}
