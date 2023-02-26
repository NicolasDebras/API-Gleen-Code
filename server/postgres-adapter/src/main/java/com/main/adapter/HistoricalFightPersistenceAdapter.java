package com.main.adapter;

import com.main.ApplicationError;
import com.main.functional.model.HistoricalFight;
import com.main.ports.server.HistoricalFightPersistenceSpi;
import com.main.repository.CardRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.val;
import com.main.mapper.HistoricalFightMapper;
import org.springframework.stereotype.Service;
import com.main.repository.HistoricalFightRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static io.vavr.API.Try;

@Service
@RequiredArgsConstructor
public class HistoricalFightPersistenceAdapter implements HistoricalFightPersistenceSpi {

    private final HistoricalFightRepository repository;

    private final CardRepository cardRepository;
    @Override
    @Transactional
    public Either<ApplicationError, List<HistoricalFight>> findAllByIDCard(UUID idCard) {
        val card = cardRepository.findById(idCard);
        if (card.isEmpty()) {
            return Either.left(new ApplicationError("Card not found", null, idCard, null));
        }
        val winner = repository.findByWinner(card.get())
                .map(HistoricalFightMapper::toDomainList)
                .toEither(new ApplicationError("Error while finding all historical fights by id card winner", null, idCard, null));
        val loser = repository.findByLoser(card.get())
                .map(HistoricalFightMapper::toDomainList)
                .toEither(new ApplicationError("Error while finding all historical fights by id card loser", null, idCard, null));
        if (winner.isLeft()){
            return winner;
        }
        if (loser.isLeft()){
            return loser;
        }
        return winner.flatMap(listWinnerFight -> loser.map(listLoserFight -> Stream.concat(listWinnerFight.stream(), listLoserFight.stream()).collect(Collectors.toList())));
    }

    @Override
    @Transactional
    public Either<ApplicationError, HistoricalFight> save(HistoricalFight o) {
        return Try(() -> repository.save(HistoricalFightMapper.fromDomain(o)))
                .toEither()
                .mapLeft(throwable -> new ApplicationError("Unable to save historical fight", null, o, throwable))
                .map(HistoricalFightMapper::toDomain);
    }

    @Override
    @Transactional
    public Option<HistoricalFight> findById(UUID uuid) {
        val historicalFight = repository.findById(uuid);
        if (historicalFight.isEmpty()) {
            return Option.none();
        }
        return Option.of(HistoricalFightMapper.toDomain(historicalFight.get()));
    }
}
