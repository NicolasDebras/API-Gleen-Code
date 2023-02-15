package com.main.functional.service;

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

import static io.vavr.API.Right;
import static org.mockito.Mockito.when;
import static org.assertj.vavr.api.VavrAssertions.assertThat;


@ExtendWith(MockitoExtension.class)
class HeroCreatorServiceTest {

    @InjectMocks private  HeroCreatorService service;

    @Mock private HeroPersistenceSpi spi;

    @Mock private RarityPersistenceSpi spiRarity;

    @Mock private SpecialityPersistenceSpi spiSpeciality;

    @Test
    @DisplayName("should create hero")
    void should_create_hero() {
        val specialityHeroDco = Speciality.builder().name("Wizard").build();
        val rarityHeroDco = Rarity.builder().name("Common").build();
        val given = Hero.builder().name("test").speciality(specialityHeroDco).rarity(rarityHeroDco).build();
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
                .name("test")
                .speciality(specialityDao)
                .rarity(rarityDao)
                .build();
        when(spi.save(given)).thenReturn(Right(given));
        when(spiRarity.findByName(rarityHeroDco.getName())).thenReturn(Option.of(rarityDao));
        when(spiSpeciality.findByName(specialityHeroDco.getName())).thenReturn(Option.of(specialityDao));

        val actual = service.create(given);
        assertThat(actual).containsRightSame(result);
    }
}