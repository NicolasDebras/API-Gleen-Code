package com.main.rest.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record HeroCreationDto(@JsonProperty("id")UUID id) {}
