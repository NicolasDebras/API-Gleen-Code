package com.main.functional.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class DeckConfiguration {

    private final String name;

    private final int tokenPay;

    private final int numberCards;


    private List<RarityDeck> rarityDeckList;
}
