package com.main.ports.server;

import com.main.functional.model.Hero;
import io.vavr.control.Option;

import java.util.UUID;


public interface HeroPersistenceSpi extends PersistenceSpi<Hero, UUID>{


    Option<Hero> findByRarityDraw(UUID idRarity);

}
