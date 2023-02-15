package com.main.functional.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Rarity {

    String name;

    Double percentage;

}
