package com.main.functional.model;


import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class RarityDeckSetting {

    UUID idRarity;

    Double percentageLucky;
}
