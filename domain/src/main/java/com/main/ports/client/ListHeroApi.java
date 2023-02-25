package com.main.ports.client;

import com.main.ApplicationError;
import com.main.functional.model.Hero;
import io.vavr.control.Either;

import java.util.List;

public interface ListHeroApi {

    Either<ApplicationError, List<Hero>> list();
}
