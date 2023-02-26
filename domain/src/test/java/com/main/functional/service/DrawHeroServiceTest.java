package com.main.functional.service;


import com.main.ApplicationError;
import com.main.functional.model.*;
import com.main.ports.server.HeroPersistenceSpi;
import com.main.ports.server.LevelPersistenceSpi;
import io.vavr.control.Option;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DrawHeroServiceTest {

    @Mock
    private HeroPersistenceSpi heroPersistenceSpi;

    @Mock
    private LevelPersistenceSpi levelPersistenceSpi;

    @InjectMocks
    private DrawHeroService drawHeroService;

    @Test
    @DisplayName("should draw 3 hero for deck Card")
    void should_draw_Hero() {
        val seed = 100;
        val percentageLuckyTotal = 1.0;
        val uuidRarityLegendary = UUID.randomUUID();
        val uuidRarityRare = UUID.randomUUID();
        val uuidRarityCommon = UUID.randomUUID();
        val uuidLevel = UUID.randomUUID();
        val rarityDeckSettings = List.of(
                RarityDeckSetting.builder()
                        .idRarity(uuidRarityLegendary)
                        .percentageLucky(0.05)
                        .build(),
                RarityDeckSetting.builder()
                        .idRarity(uuidRarityRare)
                        .percentageLucky(0.2)
                        .build(),
                RarityDeckSetting.builder()
                        .idRarity(uuidRarityCommon)
                        .percentageLucky(0.75) // 1
                        .build()
        );
        val deckSetting = DeckSetting.builder()
                .nameDeckType("Silver")
                .numberCard(3)
                .rarityDeckSettings(rarityDeckSettings)
                .build();
        val level = Level.builder()
                .id(uuidLevel)
                .level(1)
                .exp(0.00)
                .build();
        val speciality = Speciality.builder()
                .name("Wizard")
                .build();
        val rarityCommon = Rarity.builder()
                .name("Common")
                .percentage(0.75)
                .build();
        val uuidHero = UUID.randomUUID();
        val heroSecondDraw = Hero.builder()
                .id(uuidHero)
                .speciality(speciality)
                .rarity(rarityCommon)
                .name("Hero 2")
                .build();
        val heroThirdDraw = Hero.builder()
                .id(uuidHero)
                .rarity(rarityCommon)
                .speciality(speciality)
                .name("Hero 3")
                .build();
        when(heroPersistenceSpi.findByRarityDraw(eq(uuidRarityCommon))).thenReturn(Option.of(heroThirdDraw));
        when(heroPersistenceSpi.findByRarityDraw(eq(uuidRarityRare))).thenReturn(Option.of(heroSecondDraw));
        when(levelPersistenceSpi.findByLevel(1)).thenReturn(Option.of(level));
        val actual = drawHeroService.drawDeck(seed, deckSetting, percentageLuckyTotal);
        val cardFistDraw = Card.builder()
                .id(UUID.randomUUID())
                .heroType(heroThirdDraw)
                .level(level)
                .build();
        val cardSecondDraw = Card.builder()
                .id(UUID.randomUUID())
                .heroType(heroSecondDraw)
                .level(level)
                .build();
        val cardThirdDraw = Card.builder()
                .id(UUID.randomUUID())
                .heroType(heroThirdDraw)
                .level(level)
                .build();
        val expected = Deck.builder()
                .cards(List.of(cardFistDraw, cardSecondDraw, cardThirdDraw))
                .build();
        assertEquals(actual.get().getCards().get(1).getHeroType(), expected.getCards().get(1).getHeroType());

    }

    @Test
    @DisplayName("Is not possible to find hero")
    void should_hero_not_find() {
        val seed = 100;
        val percentageLuckyTotal = 1.0;
        val uuidRarityLegendary = UUID.randomUUID();
        val uuidRarityRare = UUID.randomUUID();
        val uuidRarityCommon = UUID.randomUUID();
        val raritySettingCommon = RarityDeckSetting.builder()
                .idRarity(uuidRarityCommon)
                .percentageLucky(0.75) // 1
                .build();
        val rarityDeckSettings = List.of(
                RarityDeckSetting.builder()
                        .idRarity(uuidRarityLegendary)
                        .percentageLucky(0.05)
                        .build(),
                RarityDeckSetting.builder()
                        .idRarity(uuidRarityRare)
                        .percentageLucky(0.2)
                        .build(),
                raritySettingCommon

        );
        val deckSetting = DeckSetting.builder()
                .nameDeckType("Silver")
                .numberCard(3)
                .rarityDeckSettings(rarityDeckSettings)
                .build();
        when(heroPersistenceSpi.findByRarityDraw(eq(uuidRarityCommon))).thenReturn(Option.none());
        val error = new ApplicationError("Not possible to find rarity", null, raritySettingCommon, null);
        val actual = drawHeroService.drawDeck(seed, deckSetting, percentageLuckyTotal);
        assertThat(actual).containsOnLeft(error);

    }

    @Test
    @DisplayName("Is not possible to find level")
    void should_level_not_find() {
        val seed = 100;
        val percentageLuckyTotal = 1.0;
        val uuidRarityLegendary = UUID.randomUUID();
        val uuidRarityRare = UUID.randomUUID();
        val uuidRarityCommon = UUID.randomUUID();
        val rarityDeckSettings = List.of(
                RarityDeckSetting.builder()
                        .idRarity(uuidRarityLegendary)
                        .percentageLucky(0.05)
                        .build(),
                RarityDeckSetting.builder()
                        .idRarity(uuidRarityRare)
                        .percentageLucky(0.2)
                        .build(),
                RarityDeckSetting.builder()
                        .idRarity(uuidRarityCommon)
                        .percentageLucky(0.75) // 1
                        .build()
        );
        val deckSetting = DeckSetting.builder()
                .nameDeckType("Silver")
                .numberCard(3)
                .rarityDeckSettings(rarityDeckSettings)
                .build();
        val speciality = Speciality.builder()
                .name("Wizard")
                .build();
        val rarityCommon = Rarity.builder()
                .name("Common")
                .percentage(0.75)
                .build();
        val uuidHero = UUID.randomUUID();
        val heroSecondDraw = Hero.builder()
                .id(uuidHero)
                .speciality(speciality)
                .rarity(rarityCommon)
                .name("Hero 2")
                .build();
        val heroThirdDraw = Hero.builder()
                .id(uuidHero)
                .rarity(rarityCommon)
                .speciality(speciality)
                .name("Hero 3")
                .build();
        when(heroPersistenceSpi.findByRarityDraw(eq(uuidRarityCommon))).thenReturn(Option.of(heroThirdDraw));
        when(heroPersistenceSpi.findByRarityDraw(eq(uuidRarityRare))).thenReturn(Option.of(heroSecondDraw));
        when(levelPersistenceSpi.findByLevel(1)).thenReturn(Option.none());
        val actual = drawHeroService.drawDeck(seed, deckSetting, percentageLuckyTotal);
        val error = new ApplicationError("Not possible to find level", null, 1, null);
        assertThat(actual).containsOnLeft(error);

    }


}