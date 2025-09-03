package com.danimo.establishment.location.domain;

import com.danimo.establishment.common.domain.annotations.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@DomainEntity
@AllArgsConstructor
public class Location {

    private UUID id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zip;
    private String phone;
    private Integer capacity;
    private boolean available;
}
