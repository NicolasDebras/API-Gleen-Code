package com.main.ports.client;

import com.main.ApplicationError;
import com.main.functional.model.HistoricalFight;
import io.vavr.control.Either;

import java.util.List;
import java.util.UUID;

public interface FindHistoricalFightCardApi{

    Either<ApplicationError, List<HistoricalFight>> find(UUID idCard);
}
