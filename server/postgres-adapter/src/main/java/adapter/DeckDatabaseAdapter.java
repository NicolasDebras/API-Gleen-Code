package adapter;

import com.main.ApplicationError;
import com.main.functional.model.Card;
import com.main.functional.model.Deck;
import com.main.ports.server.DeckPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import mapper.CardEntityMapper;
import org.springframework.stereotype.Service;
import repository.CardRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeckDatabaseAdapter implements DeckPersistenceSpi {

    private final CardRepository repository;

    @Override
    public Either<ApplicationError, Deck> save(UUID idUser, Deck deck) {
        return null;
    }

    @Override
    public Either<ApplicationError, Deck> find(UUID idUser) {
        return repository.findAllByUser(idUser)
                .map(CardEntityMapper::toDomainDeck)
                .toEither(new ApplicationError("Error while finding deck by id user", null, idUser, null));
    }

    @Override
    public Either<ApplicationError, Deck> save(Deck o) {
        return null;
    }

    @Override
    public Option<Deck> findById(UUID uuid) {
        return repository.findById(uuid)
                .map(CardEntityMapper::toDomain);
    }
}
