package com.main.domain.functionnal.service;

import com.main.domain.ApplicationError;
import com.main.domain.functionnal.model.Hero;
import com.main.domain.functionnal.service.validation.HeroValidator;
import com.main.domain.ports.client.HeroCreatorApi;
import com.main.domain.ports.server.HeroPersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class HeroCreatorService implements HeroCreatorApi {

    private final HeroPersistenceSpi spi;
    @Override
    public Either<ApplicationError, Hero> create(Hero hero) {
        return HeroValidator.validate(hero)
                .toEither()
                .peekLeft(
                        error  -> log.error("An error occurred while validating hero : {}", error))
                .flatMap(spi::save);
    }
}
