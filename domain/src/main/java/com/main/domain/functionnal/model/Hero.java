package com.main.domain.functionnal.model;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Value
@Builder
public class Hero {
    @Builder.Default
    UUID id = UUID.randomUUID();
    @With  String name;
    @With @Builder.Default int pv = 0;
    @With @Builder.Default int xp = 0;
    @With @Builder.Default int power = 0;
    @With @Builder.Default int armor = 0;
    @With @Builder.Default int level = 0;
    @With @Builder.Default int experiencePoint = 0;
    @With SpecialityHero specialityHero;
    @With RarityHero rarityHero;


}
