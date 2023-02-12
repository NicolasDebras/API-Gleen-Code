package com.main.functional.model;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Hero {

    private String name;
    private int attack;

    private int power;

    private int armor;
    private Speciality speciality;
    private Rarity rarity;
}
