package com.main.ports.server;

import com.main.functional.model.Rarity;
import io.vavr.control.Option;

public interface RarityPersistenceSpi extends PersistenceSpi<Rarity, String>{

    Option<Rarity> findByName(String name);
}
