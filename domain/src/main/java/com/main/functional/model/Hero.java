package com.main.functional.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class Hero {

    @Builder.Default
    UUID id = UUID.randomUUID();

    String name;

    Speciality speciality;

    Rarity rarity;
}
