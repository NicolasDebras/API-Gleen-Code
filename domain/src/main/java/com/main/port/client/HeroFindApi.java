package com.main.port.client;

import com.main.ApplicationError;
import com.main.functional.model.HeroCreate;
import com.main.functional.model.HeroFind;
import io.vavr.control.Either;

import java.util.List;

public interface HeroFindApi {

    Either<ApplicationError, List<HeroCreate>> findHeroByFilter(HeroFind heroFind);
}
