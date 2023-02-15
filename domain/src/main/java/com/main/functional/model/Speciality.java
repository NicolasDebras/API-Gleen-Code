package com.main.functional.model;



import lombok.Builder;
import lombok.Value;

import java.util.List;


@Value
@Builder
public class Speciality {

    String name;

    int power;

    int health;

    int armor;

    List<AdvantageOtherHero> advantageOtherHeroes;

}
