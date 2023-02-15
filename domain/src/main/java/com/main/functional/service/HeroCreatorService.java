package com.main.functional.service;

import com.main.ApplicationError;
import com.main.functional.model.Hero;
import com.main.functional.model.Rarity;
import com.main.functional.model.Speciality;
import com.main.functional.service.validation.HeroCreatedValidation;
import com.main.ports.client.HeroCreatorApi;
import com.main.ports.server.HeroPersistenceSpi;
import com.main.ports.server.RarityPersistenceSpi;
import com.main.ports.server.SpecialityPersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
public class HeroCreatorService implements HeroCreatorApi {

    private final HeroPersistenceSpi spi;

    private final RarityPersistenceSpi raritySpi;

    private final SpecialityPersistenceSpi specialitySpi;

    @Override
    public Either<ApplicationError, Hero> create(Hero hero) {
        return HeroCreatedValidation.validate(hero)
                .toEither()
                .peekLeft(error -> log.error("An error occurred while validating hero : {}", error))
                .flatMap(spi::save)
                .peekLeft(error -> log.error("An error occurred while saving hero : {}", error))
                .flatMap(heroSaved -> buildHeroReturn(heroSaved, heroSaved.getId()));
    }

    private Either<ApplicationError, Hero> buildHeroReturn(Hero hero, UUID id) {
        return findRarity(hero.getRarity().getName())
                .peekLeft(error -> log.error("An error occurred while finding rarity : {}", error))
                .flatMap(rarity -> findSpeciality(hero.getSpeciality().getName())
                        .peekLeft(error -> log.error("An error occurred while finding speciality : {}", error))
                        .map(speciality -> Hero.builder()
                                .id(id)
                                .name(hero.getName())
                                .rarity(rarity)
                                .speciality(speciality).build())
                );
    }

    private Either<ApplicationError, Rarity> findRarity(String rarity) {
        return raritySpi.findByName(rarity)
                .map(
                        Either::<ApplicationError, Rarity>right).getOrElse(
                        () -> Either.left(new ApplicationError("Not found Rarity", null, rarity, null)));
    }

    private Either<ApplicationError, Speciality> findSpeciality(String speciality) {
        return specialitySpi.findByName(speciality)
                .map(
                        Either::<ApplicationError, Speciality>right).getOrElse(
                        () -> Either.left(new ApplicationError("Not found Speciality", null, speciality, null)));
    }


}
