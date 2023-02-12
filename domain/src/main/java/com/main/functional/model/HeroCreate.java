package com.main.functional.model;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class HeroCreate {

    private String name;
    private String speciality;
    private String rarity;
}
