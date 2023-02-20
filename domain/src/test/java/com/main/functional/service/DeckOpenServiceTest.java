package com.main.functional.service;

import com.main.functional.model.*;
import com.main.ports.server.DeckPersistenceSpi;
import com.main.ports.server.SettingDeckPersistenceSpi;
import com.main.ports.server.UserAccountPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static io.vavr.API.Right;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DeckOpenServiceTest {

    @InjectMocks
    private DeckOpenService deckOpenService;

    @Mock
    private UserAccountPersistenceSpi userAccountPersistenceSpi;

    @Mock
    private SettingDeckPersistenceSpi settingDeckPersistenceSpi;

    @Mock
    private DrawHeroService drawHeroService;

    @Mock
    private DeckPersistenceSpi deckPersistenceSpi;

    @Test
    void should_create_Deck() {
        val idDeckDto = UUID.randomUUID();
        val deckDto = DeckDTO.builder()
                .idUser(idDeckDto)
                .nameDeck("nameDeck")
                .build();
        val idUser = UUID.randomUUID();
        val user = User.builder()
                .id(idUser)
                .token(4)
                .build();
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
        val deckSetting = DeckSetting.builder()
                .nameDeckType("nameDeck")
                .token(4)
                .numberCard(3)
                .rarityDeckSettings(rarityDeckSettings)
                .build();
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
        val deck = Deck.builder()
                .cards(List.of(cardFistDraw, cardSecondDraw, cardThirdDraw))
                .build();
        when(userAccountPersistenceSpi.findById(any(UUID.class))).thenReturn(Option.of(user));
        when(settingDeckPersistenceSpi.findByName("nameDeck")).thenReturn(Option.of(deckSetting));
        when(drawHeroService.drawDeck(1832989864, deckSetting,1.0 )).thenReturn(Right(deck));
        when(deckPersistenceSpi.save(any(UUID.class), any(Deck.class))).thenReturn(Right(deck));
        val result = deckOpenService.create(deckDto);
        assert result.isRight();
    }



}