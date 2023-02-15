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
import lombok.val;



@Slf4j
@RequiredArgsConstructor
public class HeroCreatorService implements HeroCreatorApi {

    private final HeroPersistenceSpi spi;

    private final RarityPersistenceSpi raritySpi;

    private final SpecialityPersistenceSpi specialitySpi;

    @Override
    public Either<ApplicationError, Hero> create(Hero hero) {
        val heroValidated = HeroCreatedValidation.validate(hero);
        if(!heroValidated.isValid()){
            return Either.left(new ApplicationError("Not valid Hero", null, hero, null));
        }
        val rarity = findRarity(hero.getRarity().getName());
        if(rarity.isLeft()){
            log.error("An error occurred while validating hero : {}", rarity.getLeft());
            return Either.left(rarity.getLeft());
        }
        val speciality = findSpeciality(hero.getSpeciality().getName());
        if(speciality.isLeft()){
            log.error("An error occurred while validating hero : {}", speciality.getLeft());
            return Either.left(speciality.getLeft());
        }
        val heroSaved = spi.save(hero);
        if(heroSaved.isLeft()){
            log.error("An error occurred while validating hero : {}", heroSaved.getLeft());
            return Either.left(heroSaved.getLeft());
        }
        return Either.right(buildHeroReturn(heroValidated.get(), speciality.get(), rarity.get()));

    }

    private Hero buildHeroReturn(Hero hero, Speciality speciality, Rarity rarity) {
        return Hero.builder()
                .id(hero.getId())
                .name(hero.getName())
                .speciality(speciality)
                .rarity(rarity)
                .build();
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
