package com.main.ports.client;


import com.main.ApplicationError;
import com.main.functional.model.Hero;
import io.vavr.control.Either;
public interface HeroCreatorApi {

    Either<ApplicationError, Hero> create(Hero hero);
}
