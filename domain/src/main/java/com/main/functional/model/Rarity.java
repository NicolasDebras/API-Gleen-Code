package com.main.functional.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class Rarity {

    @Builder.Default
    UUID id = UUID.randomUUID();

    String name;

    Double percentage;

}
