package com.danimo.establishment.opinions.infrastructure.outputadapters.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "opinions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpinionDbEntity {
    @Id
    private UUID id;
    @Column
    private UUID locationId;
    @Column
    private String username;
    @Column
    private Integer score;
    @Column
    private String opinionTitle;
    @Column
    private String comment;
    @Column
    private LocalDateTime createdAt;
}
