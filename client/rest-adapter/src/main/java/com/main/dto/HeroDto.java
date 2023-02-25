package com.main.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record HeroDto (

    UUID id,
    String name,
    String speciality,

    String rarity
){}
