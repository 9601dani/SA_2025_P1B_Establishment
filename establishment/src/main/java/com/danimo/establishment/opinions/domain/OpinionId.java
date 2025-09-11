package com.danimo.establishment.opinions.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class OpinionId {
    private UUID id;

    public OpinionId(UUID id) {
        this.id = id;
    }

    public static OpinionId generate(){
        return new OpinionId(UUID.randomUUID());
    }

    public static OpinionId fromUuid(UUID uuid){
        return new OpinionId(uuid);
    }
}
