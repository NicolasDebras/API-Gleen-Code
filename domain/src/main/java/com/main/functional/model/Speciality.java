package com.main.functional.model;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Speciality {

    private String name;

    private int attack;
    private int defense;

    private int health;

}
