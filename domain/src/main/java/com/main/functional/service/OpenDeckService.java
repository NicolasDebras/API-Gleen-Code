package com.main.functional.service;

import com.main.functional.model.Hero;
import com.main.functional.model.OpenDeck;
import com.main.functional.utils.RandomSeedDeck;
import com.main.port.client.DeckCreateApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;


@Slf4j
@RequiredArgsConstructor
public class OpenDeckService implements DeckCreateApi {

    private final RandomSeedDeck randomSeedDeck;


    @Override
    public List<Hero> openDeck(OpenDeck openDeck) {
        try{
            Random random = new Random();

            //return randomSeedDeck.randomSeedDeck(random.nextInt(1, 1000), openDeck);
        } catch (Exception e){
            log.error("Error open deck", e);
            return null;
        }
        return null;
    }

    //TODO: Implementer la methode qui recupère les type de carte  en fonction du deck
    //Todo: Implementer la fonction qui tire ces cartes
    //Todo: Implementer le builder de la des heros tirès au sort juste le level 1 et l'expérience 0 leur spécialiter et leur rareté
    // et leur avantage avec les autres cartes

}
