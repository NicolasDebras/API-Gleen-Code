package com.main.functional.service;

import com.main.ApplicationError;
import com.main.functional.model.Hero;
import com.main.ports.client.ListHeroApi;
import com.main.ports.server.HeroPersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
public class ListHeroService implements ListHeroApi {

    private final HeroPersistenceSpi heroPersistenceSpi;

    @Override
    public Either<ApplicationError, List<Hero>> list() {
        val heroes = heroPersistenceSpi.findAll();
        if (heroes.isEmpty()){
            return Either.left(new ApplicationError("Not possible to list heroes", null, null, null));
        }
        if(heroes.get().isEmpty()){
            return Either.left(new ApplicationError("Not hero created", null, null, null));
        }
        return Either.right(heroes.get());
    }
}
