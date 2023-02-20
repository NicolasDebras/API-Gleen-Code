package com.main.functional.model;


import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Value
public class HistoricalFight {
    UUID winner;

    UUID loser;

    @Builder.Default
    LocalDateTime date = LocalDateTime.now();
}
