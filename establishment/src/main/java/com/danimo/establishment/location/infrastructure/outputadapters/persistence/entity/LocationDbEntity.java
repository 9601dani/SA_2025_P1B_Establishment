package com.danimo.establishment.location.infrastructure.outputadapters.persistence.entity;

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
@Table(name = "location")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDbEntity {
    @Id
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String city;
    @Column
    private String state;
    @Column
    private String country;
    @Column
    private String zip;
    @Column
    private String phone;
    @Column
    private Integer capacity;
    @Column
    private boolean available;
    @Column
    private String description;
    @Column
    private String type;
    @Column
    private LocalDateTime createdAt;
    @Column
    private String imageUrl;

}
