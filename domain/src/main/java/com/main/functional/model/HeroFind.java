package com.main.functional.model;


import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
public class HeroFind {

    @With
    @Builder.Default
    private int offset=0;
    private int limit;
}
