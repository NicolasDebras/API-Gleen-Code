package adapter;

import com.main.ApplicationError;
import com.main.functional.model.Card;
import com.main.ports.server.CardPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.val;
import mapper.CardEntityMapper;
import org.springframework.stereotype.Service;
import repository.CardRepository;
import repository.LevelRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardDatabaseAdapter implements CardPersistenceSpi {

    private final CardRepository cardRepository;

    private final LevelRepository levelRepository;
    @Override
    public Either<ApplicationError, Card> updateExperience(Card card) {
        return cardRepository.findById(card.getId())
                .map(cardEntity -> {
                    cardEntity.setExperience(card.getExperience()+1);
                    return cardRepository.save(cardEntity);
                })
                .map(CardEntityMapper::toDomain)
                .toEither(new ApplicationError("Card not found", null, card, null));
    }

    @Override
    public Either<ApplicationError, Card> updateLevel(Card card) {
        val level = levelRepository.findByLevel(card.getLevel().getLevel()+1);
        if(level.isEmpty()){
            return Either.left(new ApplicationError("Level not found", null, card, null));
        }
        return cardRepository.findByCardId(card.getId())
                .map(cardEntity -> {
                    cardEntity.setLevel(level.get());
                    return cardRepository.save(cardEntity);
                })
                .map(CardEntityMapper::toDomain)
                .toEither(new ApplicationError("Card not found", null, card, null));
    }

    @Override
    public Either<ApplicationError, Card> save(Card o) {
        return cardRepository.save(CardEntityMapper.fromDomain(o))
                .map(CardEntityMapper::toDomain)
                .toEither(new ApplicationError("Card not saved", null, o, null));
    }

    @Override
    public Option<Card> findById(UUID uuid) {
        return cardRepository.findByCardId(uuid)
                .map(CardEntityMapper::toDomain);
    }
}
