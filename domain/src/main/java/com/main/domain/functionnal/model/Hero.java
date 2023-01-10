package com.main.domain.functionnal.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Hero {
    private String id;
    private String name;
    private int Pv;
    private int xp;
    private int power;
    private int armor;
    private Speciality speciality;
    @Builder.Default private int lvl = 0;
    private Rarity rarity;

    private int experiencePoint;
}


