package com.main.functional.service;

import com.main.ApplicationError;
import com.main.functional.model.Hero;
import com.main.functional.model.Rarity;
import com.main.functional.model.Speciality;
import com.main.port.server.HeroCreatePersistenceSpi;
import com.main.port.server.RarityPersistanceSpi;
import com.main.port.server.SpecialityPersistanceSpi;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class HeroCreatorServiceTest {

    @InjectMocks
    private HeroCreatorService service;


    @Mock
    private RarityPersistanceSpi raritySpi;

    @Mock
    private SpecialityPersistanceSpi specialitySpi;


    @Mock
    private HeroCreatePersistenceSpi spi;



    @Test
    void create() {
        // Given
        val rarityName = "Legendary";
        val specialityName = "Wizard";
        val name = "Nicolas";
        val health = 100;
        val attack = 100;
        val defense = 100;
        val percentage = 0.20;
        val healthWithPercentage = (int) (health + (health * percentage ));
        val attackWithPercentage = (int) (attack + (attack * percentage ));
        val defenseWithPercentage = (int) (defense + (defense * percentage ));

        val heroName = Hero.builder()
                .name(name)
                .speciality(specialityName)
                .rarity(rarityName)
                .build();
        val speciality = Speciality.builder()
                .name(specialityName)
                .health(health)
                .attack(attack)
                .defense(defense)
                .build();
        val rarity = Rarity.builder()
                .name(rarityName)
                .percentage(percentage)
                .build();
        val hero = Hero.builder()
                .name(name)
                .speciality(heroName.getSpeciality())
                .rarity(heroName.getRarity())
                .attack(attackWithPercentage)
                .defense(defenseWithPercentage)
                .health(healthWithPercentage)
                .build();
        when(spi.save(heroName)).thenReturn(heroName);
        when(raritySpi.findByName(rarityName)).thenReturn(Optional.of(rarity));
        when(specialitySpi.findByName(specialityName)).thenReturn(Optional.of(speciality));
        val actual = service.create(heroName);
        assertThat(actual).isEqualTo(Optional.of(hero));

    }






}