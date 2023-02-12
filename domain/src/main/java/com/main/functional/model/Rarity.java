package com.main.functional.model;


import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
public class Rarity {

    private String name;

    @With @Builder.Default
    private Double percentage = 0.0;

}
