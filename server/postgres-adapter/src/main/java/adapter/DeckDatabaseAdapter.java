package adapter;

import com.main.ApplicationError;
import com.main.functional.model.Deck;
import com.main.ports.server.DeckPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.UUID;

public class DeckDatabaseAdapter implements DeckPersistenceSpi {
    @Override
    public Either<ApplicationError, Deck> save(UUID idUser, Deck deck) {
        return null;
    }

    @Override
    public Either<ApplicationError, Deck> find(UUID idUser) {
        return null;
    }

    @Override
    public Either<ApplicationError, Deck> save(Deck o) {
        return null;
    }

    @Override
    public Option<Deck> findById(UUID uuid) {
        return null;
    }
}
