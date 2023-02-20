package com.main.ports.client;

import com.main.ApplicationError;
import com.main.functional.model.Fighter;
import io.vavr.control.Either;

public interface FightCardApi {

    Either<ApplicationError, Fighter> fightCard(Fighter fighter);
}
