package com.danimo.establishment.opinions.domain;

import lombok.Value;

@Value
public class OpinionScore {
    private int opinion;

    public OpinionScore(int opinion) {
        if(opinion < 1 || opinion > 5){
            throw new IllegalArgumentException("El rango de la opinion debe ser entre 1 y 5");
        }
        this.opinion = opinion;
    }
    public static OpinionScore fromInt(int opinion) {
        return new OpinionScore(opinion);
    }

}
