package com.main.functional.model;


import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class AdvantageOtherHero {

    @Builder.Default
    UUID id = UUID.randomUUID();
    int power;

    String name;

}
