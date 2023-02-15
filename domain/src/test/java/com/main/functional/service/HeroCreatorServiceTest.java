package com.main.functional.service;

import com.main.ApplicationError;
import com.main.functional.model.AdvantageOtherHero;
import com.main.functional.model.Hero;
import com.main.functional.model.Rarity;
import com.main.functional.model.Speciality;
import com.main.ports.server.HeroPersistenceSpi;
import com.main.ports.server.RarityPersistenceSpi;
import com.main.ports.server.SpecialityPersistenceSpi;
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

import static io.vavr.API.Left;
import static io.vavr.API.Right;
import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class HeroCreatorServiceTest {

    @InjectMocks private  HeroCreatorService service;

    @Mock private HeroPersistenceSpi spi;

    @Mock private RarityPersistenceSpi spiRarity;

    @Mock private SpecialityPersistenceSpi spiSpeciality;

    @Test
    @DisplayName("should create hero")
    void should_create_hero() {
        val idHero = UUID.randomUUID();
        val specialityHeroDco = Speciality.builder().name("Wizard").build();
        val rarityHeroDco = Rarity.builder().name("Common").build();
        val given = Hero.builder()
                .id(idHero)
                .name("test")
                .speciality(specialityHeroDco)
                .rarity(rarityHeroDco).build();
        val advantageOtherHeroDao = AdvantageOtherHero.builder()
                .name("Assassin")
                .power(25)
                .build();
        val rarityDao = Rarity.builder()
                .name("Common")
                .percentage(0.0)
                .build();
        val specialityDao = Speciality.builder()
                .name("Wizard")
                .health(700)
                .power(150)
                .armor(10)
                .advantageOtherHeroes(List.of(advantageOtherHeroDao))
                .build();
        val result = Hero.builder()
                .id(idHero)
                .name("test")
                .speciality(specialityDao)
                .rarity(rarityDao)
                .build();
        when(spi.save(given)).thenReturn(Right(given));
        when(spiRarity.findByName(rarityHeroDco.getName())).thenReturn(Option.of(rarityDao));
        when(spiSpeciality.findByName(specialityHeroDco.getName())).thenReturn(Option.of(specialityDao));

        val actual = service.create(given);

        //Working with Junit
        assertEquals(actual.get(), result);
        verifyNoMoreInteractions(spiRarity);
        verifyNoMoreInteractions(spiSpeciality);
        verifyNoMoreInteractions(spi);
        //Not working with AssertJ
        assertThat(actual).containsRightSame(result);




    }

    @Test
    @DisplayName("should create hero with  rarity not found")
    void should_create_hero_with_rarity_not_found() {
        val idHero = UUID.randomUUID();
        val specialityHeroDco = Speciality.builder().name("Wizard").build();
        val rarityHeroDco = Rarity.builder().name("Common").build();
        val given = Hero.builder()
                .id(idHero)
                .name("test")
                .speciality(specialityHeroDco)
                .rarity(rarityHeroDco).build();
        when(spiRarity.findByName(rarityHeroDco.getName())).thenReturn(Option.none());
        val actual = service.create(given);
        assertThat(actual).containsLeftInstanceOf(ApplicationError.class);
        verifyNoInteractions(spi);
    }

    @Test
    @DisplayName("should create hero with  rarity not found")
    void should_create_hero_with_speciality_not_found() {
        val idHero = UUID.randomUUID();
        val specialityHeroDco = Speciality.builder().name("Wizard").build();
        val rarityHeroDco = Rarity.builder().name("Common").build();
        val given = Hero.builder()
                .id(idHero)
                .name("test")
                .speciality(specialityHeroDco)
                .rarity(rarityHeroDco).build();
        when(spiRarity.findByName(rarityHeroDco.getName())).thenReturn(Option.none());
        val actual = service.create(given);
        assertThat(actual).containsLeftInstanceOf(ApplicationError.class);
    }

    @Test
    @DisplayName("should not create hero if technical error occurred in adapter")
    void should_not_create_hero_if_technical_error_occurred_in_adapter() {
        val idHero = UUID.randomUUID();
        val specialityHeroDco = Speciality.builder().name("Wizard").build();
        val rarityHeroDco = Rarity.builder().name("Common").build();
        val given = Hero.builder()
                .id(idHero)
                .name("test")
                .speciality(specialityHeroDco)
                .rarity(rarityHeroDco).build();
        val advantageOtherHeroDao = AdvantageOtherHero.builder()
                .name("Assassin")
                .power(25)
                .build();
        val rarityDao = Rarity.builder()
                .name("Common")
                .percentage(0.0)
                .build();
        val specialityDao = Speciality.builder()
                .name("Wizard")
                .health(700)
                .power(150)
                .armor(10)
                .advantageOtherHeroes(List.of(advantageOtherHeroDao))
                .build();
        val error = new ApplicationError("An error occurred", null, null, null);
        when(spi.save(given)).thenReturn(Left(error));
        when(spiRarity.findByName(rarityHeroDco.getName())).thenReturn(Option.of(rarityDao));
        when(spiSpeciality.findByName(specialityHeroDco.getName())).thenReturn(Option.of(specialityDao));
        val actual = service.create(given);
        assertThat(actual).containsLeftSame(error);
    }


}