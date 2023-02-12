package com.main.functional.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;


@Value
@Builder
public class OpenDeck {

    private final String packName;

    private final UUID idPlayer;
}
