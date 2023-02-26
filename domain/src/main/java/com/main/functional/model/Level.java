package com.main.functional.model;


import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Value
@Builder
public class Level {
    UUID id;
    int level;

    @With @Builder.Default
    Double exp=0.00;

}
