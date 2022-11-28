package com.main.domain.ports.client;

import com.main.domain.ApplicationError;
import com.main.domain.functionnal.model.Hero;
import io.vavr.control.Either;

public interface HeroCreatorApi {
    Either<ApplicationError, Hero>  create(Hero hero);
}
