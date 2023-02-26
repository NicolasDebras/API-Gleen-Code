package com.main.ports.server;

import com.main.functional.model.Level;
import io.vavr.control.Option;

import java.util.UUID;

public interface LevelPersistenceSpi extends PersistenceSpi<Level, UUID> {

    Option<Level> findByLevel(int level);
}
