package com.main.functional.model;


import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Builder
@Value
public class Hero {

    private UUID id;

    private String name;
    private String speciality;
    private String rarity;

    private int health;

    private int attack;

    private int defense;

    @With @Builder.Default
    private int level = 1;
}
