package adapter;

import com.main.ApplicationError;
import com.main.functional.model.HistoricalFight;
import com.main.ports.server.HistoricalFightPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.val;
import mapper.HistoricalFightMapper;
import org.springframework.stereotype.Service;
import repository.HistoricalFightRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HistoricalFightPersistenceAdapter implements HistoricalFightPersistenceSpi {

    private final HistoricalFightRepository repository;
    @Override
    public Either<ApplicationError, List<HistoricalFight>> findAllByIDCard(UUID idCard) {
        return repository.findAllByIDCard(idCard)
                .map(HistoricalFightMapper::toDomainList)
                .toEither(new ApplicationError("Error while finding all historical fights by id card", null, idCard, null));
    }

    @Override
    public Either<ApplicationError, HistoricalFight> save(HistoricalFight o) {
        val historicalFight = repository.save(HistoricalFightMapper.fromDomain(o));
        return Either.right(HistoricalFightMapper.toDomain(historicalFight));
    }

    @Override
    public Option<HistoricalFight> findById(UUID uuid) {
        val historicalFight = repository.findById(uuid);
        if (historicalFight.isEmpty()) {
            return Option.none();
        }
        return Option.of(HistoricalFightMapper.toDomain(historicalFight.get()));
    }
}
