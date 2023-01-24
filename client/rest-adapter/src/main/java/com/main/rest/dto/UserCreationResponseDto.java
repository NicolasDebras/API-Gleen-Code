package com.main.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserCreationResponseDto(

        String id,
        String username,

        @JsonProperty("jeton")
        int jeton
) {
}
