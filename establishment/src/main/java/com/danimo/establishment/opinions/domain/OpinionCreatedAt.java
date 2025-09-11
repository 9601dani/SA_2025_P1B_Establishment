package com.danimo.establishment.opinions.domain;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class OpinionCreatedAt {
    private LocalDateTime createdAt;

    public OpinionCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static OpinionCreatedAt generate(){
        return new OpinionCreatedAt(LocalDateTime.now());
    }

    public static OpinionCreatedAt fromDate(LocalDateTime createdAt) {
        return new OpinionCreatedAt(createdAt);
    }
}
