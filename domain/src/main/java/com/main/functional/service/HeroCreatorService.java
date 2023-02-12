package com.main.functional.service;

import com.main.functional.model.Hero;
import com.main.functional.model.Rarity;
import com.main.functional.model.Speciality;
import com.main.functional.service.validation.HeroCreateValidator;
import com.main.port.client.HeroCreatorApi;
import com.main.port.server.HeroCreatePersistenceSpi;

import com.main.port.server.RarityPersistanceSpi;
import com.main.port.server.SpecialityPersistanceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class HeroCreatorService implements HeroCreatorApi {

    private final HeroCreatePersistenceSpi spi;

    private final SpecialityPersistanceSpi specialitySpi;

    private final RarityPersistanceSpi raritySpi;


    @Override
    public Optional<Hero> create(Hero hero) {
        try{
            val speciality = findSpeciality(hero.getSpeciality());
            val rarity = findRarity(hero.getRarity());
            return HeroCreateValidator.validate(hero)
                    .map(heroValidate -> spi.save(hero))
                    .map(heroSave -> createHeroReturn(heroSave, speciality, rarity));
        } catch (Exception e){
            log.error("Error creating hero", e);
            return Optional.empty();
        }

    }

    private Speciality findSpeciality(String speciality) {
        return specialitySpi.findByName(speciality).orElseThrow(() -> new IllegalArgumentException("Speciality not found"));
    }


    private Rarity findRarity(String rarity) {
        return raritySpi.findByName(rarity).orElseThrow(() -> new IllegalArgumentException("Rarity not found"));
    }

    private Hero createHeroReturn(Hero hero, Speciality speciality, Rarity rarity) {
        val health = calculateValueWithPercentage(speciality.getHealth(), rarity.getPercentage());
        val attack = calculateValueWithPercentage(speciality.getAttack(), rarity.getPercentage());
        val defense = calculateValueWithPercentage(speciality.getDefense(), rarity.getPercentage());
        return Hero.builder()
                .name(hero.getName())
                .speciality(hero.getSpeciality())
                .rarity(hero.getRarity())
                .health(health)
                .attack(attack)
                .defense(defense)
                .build();
    }

    public int calculateValueWithPercentage(int value, Double percentage) {
        return (int) (value + (value * percentage));
    }


}
