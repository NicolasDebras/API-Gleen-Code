package com.main.domain.ports.server;

import com.main.domain.functionnal.model.Hero;

import java.util.UUID;

public interface HeroPersistenceSpi extends PersistenceSpi<Hero, UUID> {}
