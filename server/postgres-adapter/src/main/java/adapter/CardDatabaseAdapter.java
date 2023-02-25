package adapter;

import com.main.ApplicationError;
import com.main.functional.model.Card;
import com.main.ports.server.CardPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.CardRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardDatabaseAdapter implements CardPersistenceSpi {

    private final CardRepository cardRepository;
    @Override
    public Either<ApplicationError, Card> updateExperience(Card card) {
        return null;
    }

    @Override
    public Either<ApplicationError, Card> updateLevel(Card card) {
        return null;
    }

    @Override
    public Either<ApplicationError, Card> save(Card o) {
        return null;
    }

    @Override
    public Option<Card> findById(UUID uuid) {
        return null;
    }
}
