package com.main.port.server;

import com.main.functional.model.Hero;
import com.main.functional.model.Rarity;
import com.main.functional.model.Speciality;
import io.vavr.control.Option;

import java.util.UUID;

public interface HeroCreatePersistenceSpi extends PersistenceSpi<Hero, UUID> {


    Option<Rarity> findRarity(String rarityName);

    Option<Speciality> findSpeciality(String specialityName);

}

