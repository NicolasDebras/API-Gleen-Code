package com.main.functional.model;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Builder
@Value
public class Card  {

    @With @Builder.Default
    UUID id = UUID.randomUUID();
    Hero heroType;
    Level level;

    User user;

    int experience;
}
