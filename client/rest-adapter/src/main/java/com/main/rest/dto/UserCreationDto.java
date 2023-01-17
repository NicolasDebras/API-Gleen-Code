package com.main.rest.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public record UserCreationDto(
        @JsonProperty("username")
        String username
) {}
