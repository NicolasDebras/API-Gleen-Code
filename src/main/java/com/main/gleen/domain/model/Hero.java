package com.main.gleen.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class Hero {
    @Getter @Setter
    private final String id;
    @Getter @Setter
    private final String name;
    @Getter @Setter
    private final int Pv;
    @Getter @Setter
    private final int xp;
    @Getter @Setter
    private final int power;
    @Getter @Setter
    private final int armor;
    @Getter @Setter
    private final Speciality speciality;
    @Getter @Setter
    private final int lvl = 0;
    @Getter @Setter
    private final Rarity rarity;
}
