package com.main.functional.service;


import com.main.ApplicationError;
import com.main.functional.model.HeroCreate;
import com.main.functional.model.HeroFind;
import com.main.functional.service.validation.HeroFindValidator;
import com.main.port.client.HeroFindApi;
import com.main.port.server.HeroFindPersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class FindHeroResponsibilityService implements HeroFindApi {

    private final HeroFindPersistenceSpi spi;

    @Override
    public Either<ApplicationError, List<HeroCreate>> findHeroByFilter(HeroFind heroFind) {
        return HeroFindValidator.validate(heroFind)
                .toEither()
                .peekLeft(
                        error -> log.error("An error occurred while validating find Hero  : {}", error))
                .flatMap(spi::findHeroCreateByFilter);
    }
}


