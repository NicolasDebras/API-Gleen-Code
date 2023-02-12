package com.main.functional.utils;

import com.main.functional.model.DeckConfiguration;
import com.main.functional.model.RarityDeck;
import lombok.val;

import java.util.*;

public class RandomSeedDeck {

    public Map<UUID, Integer> randomSeedDeck(int seed, DeckConfiguration deck) {
        Random random = new Random(seed);
        val mapResultTirageCard = new HashMap<UUID, Integer>();
        for (int i = 0; i < deck.getNumberCards(); i++) {
            val tirageCard = random.nextDouble();
            val rarityNumber = chooseRarity(tirageCard, deck.getRarityDeckList());
            if (rarityNumber.isPresent()) {
                val rarity = rarityNumber.get();
                if (mapResultTirageCard.containsKey(rarity.getIdRarity())) {
                    mapResultTirageCard.put(rarity.getIdRarity(), mapResultTirageCard.get(rarity.getIdRarity()) + 1);
                } else {
                    mapResultTirageCard.put(rarity.getIdRarity(), 1);
                }
            }
        }

        return mapResultTirageCard;
    }

    public Optional<RarityDeck> chooseRarity(Double tirageCard, List<RarityDeck> rarityDeckList) {
        return rarityDeckList.stream()
                .filter(rarityDeck -> rarityDeck.getPercentageRarity() > tirageCard)
                .findFirst();
    }
}
