package com.main.rest.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record HeroCreationDto(@JsonProperty("driverSSNumber") String driverSSNumber) {}
