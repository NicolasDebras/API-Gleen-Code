package com.main.functional.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Deck {

    List<Card> cards;



}
