package com.main.ports.server;

import com.main.functional.model.DeckSetting;
import io.vavr.control.Option;

import java.util.UUID;

public interface SettingDeckPersistenceSpi extends PersistenceSpi<DeckSetting, UUID>{
    Option<DeckSetting> findByName(String nameTypeDeck);
}
