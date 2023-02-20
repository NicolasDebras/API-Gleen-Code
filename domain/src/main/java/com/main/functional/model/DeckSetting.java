package com.main.functional.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class DeckSetting {

    String nameDeckType;
    int token;

    int numberCard;

    List<RarityDeckSetting> rarityDeckSettings;

}
