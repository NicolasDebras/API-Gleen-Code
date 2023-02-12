package com.main.port.server;

import com.main.ApplicationError;
import com.main.functional.model.HeroCreate;
import com.main.functional.model.HeroFind;
import io.vavr.control.Either;

import java.util.List;
import java.util.UUID;

public interface HeroFindPersistenceSpi extends PersistenceSpi<HeroFind, UUID> {

    Either<ApplicationError, List<HeroCreate>> findHeroCreateByFilter(HeroFind heroFind);
}
