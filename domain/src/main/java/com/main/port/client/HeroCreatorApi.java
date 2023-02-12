package com.main.port.client;

import com.main.functional.model.Hero;

import java.util.Optional;

public interface HeroCreatorApi {
    Optional<Hero> create(Hero hero);

}
