package com.main.functional.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class DeckDTO {

    UUID idUser;

    String nameDeck;
}
