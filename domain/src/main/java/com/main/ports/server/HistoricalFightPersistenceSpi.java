package com.main.ports.server;

import com.main.ApplicationError;
import com.main.functional.model.HistoricalFight;
import io.vavr.control.Either;

import java.util.List;
import java.util.UUID;

public interface HistoricalFightPersistenceSpi extends PersistenceSpi<HistoricalFight, UUID>{

    Either<ApplicationError, List<HistoricalFight>> findAllByIDCard(UUID idCard);
}
