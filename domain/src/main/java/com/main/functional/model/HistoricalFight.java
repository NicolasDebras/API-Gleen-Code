package com.main.functional.model;


import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Value
public class HistoricalFight {

    @Builder.Default
    UUID id = UUID.randomUUID();

    Card winner;

    Card loser;

    @Builder.Default
    LocalDateTime date = LocalDateTime.now();
}
