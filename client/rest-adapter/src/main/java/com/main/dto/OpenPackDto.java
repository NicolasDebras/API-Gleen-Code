package com.main.dto;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

public record OpenPackDto(

        @JsonProperty("user")
        UUID idUser,

        @JsonProperty("pack")
        String packName

) { }
