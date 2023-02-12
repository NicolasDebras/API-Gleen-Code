package com.main.functional.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;


@Value
@Builder
public class RarityDeck {

    private String nameRarity;

    private UUID idRarity;

    private Double percentageRarity;
}
