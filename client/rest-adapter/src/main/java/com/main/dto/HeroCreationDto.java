package com.main.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;


import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record HeroCreationDto(

        @JsonProperty("name")
        String name,
        @JsonProperty("speciality")
        String speciality,

        @JsonProperty("rarity")
        String rarity
) {}
