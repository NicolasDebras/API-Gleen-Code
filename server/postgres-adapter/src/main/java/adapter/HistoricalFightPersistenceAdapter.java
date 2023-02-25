package adapter;

import com.main.ApplicationError;
import com.main.functional.model.HistoricalFight;
import com.main.ports.server.HistoricalFightPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.List;
import java.util.UUID;

public class HistoricalFightPersistenceAdapter implements HistoricalFightPersistenceSpi {
    @Override
    public Either<ApplicationError, List<HistoricalFight>> findAllByIDCard(UUID idCard) {
        return null;
    }

    @Override
    public Either<ApplicationError, HistoricalFight> save(HistoricalFight o) {
        return null;
    }

    @Override
    public Option<HistoricalFight> findById(UUID uuid) {
        return null;
    }
}
