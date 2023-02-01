package com.main.domain.functionnal.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {

    private String id;

    private String pseudo;

    @Builder.Default private int jeton=4;

    private Deck deck;

}
