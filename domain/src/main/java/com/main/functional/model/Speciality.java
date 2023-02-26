package com.main.functional.model;



import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;


@Value
@Builder
public class Speciality {

    @Builder.Default
    UUID id = UUID.randomUUID();

    String name;

    int power;

    int health;

    int armor;

    List<AdvantageOtherHero> advantageOtherHeroes;

}
