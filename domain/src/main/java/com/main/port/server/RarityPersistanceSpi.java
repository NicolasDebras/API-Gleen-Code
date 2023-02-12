package com.main.port.server;

import com.main.functional.model.Rarity;

import java.util.Optional;
import java.util.UUID;

public interface RarityPersistanceSpi extends PersistenceSpi<Rarity, UUID> {

    Optional<Rarity> findByName(String name);

}
