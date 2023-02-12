package com.main.port.client;

import com.main.functional.model.Hero;
import com.main.functional.model.OpenDeck;

import java.util.List;

public interface DeckCreateApi {

    List<Hero> openDeck(OpenDeck openDeck);
}
