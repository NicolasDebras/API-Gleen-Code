package com.main.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record HeroCreationDto(
        @JsonProperty("name")
        String name,
        @JsonProperty("pv")
        int pv,
        @JsonProperty("speciality")
        String speciality,
        @JsonProperty("rarity")
        String rarity,
        @JsonProperty("xp")
        int xp,
        @JsonProperty("power")
        int power,
        @JsonProperty("armor")
        int armor,
        @JsonProperty("level")
        int level,
        @JsonProperty("experiencePoint")
        int experiencePoint
){}
