package com.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record FightDto(

        @JsonProperty("attacker")
        UUID idCardAttacker,

        @JsonProperty("defender")
        UUID idCardDefender
) { }
