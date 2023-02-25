package adapter;

import com.main.ApplicationError;
import com.main.functional.model.DeckSetting;
import com.main.ports.server.SettingDeckPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import mapper.SettingDeckMapper;
import org.springframework.stereotype.Service;
import repository.SettingDeckRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SettingDeckDatabaseAdapter implements SettingDeckPersistenceSpi {

    private final SettingDeckRepository repository;


    @Override
    public Either<ApplicationError, DeckSetting> save(DeckSetting o) {
        return null;
    }

    @Override
    public Option<DeckSetting> findById(UUID uuid) {
        return repository.findById(uuid)
                .map(SettingDeckMapper::toDomain);
    }

    @Override
    public Option<DeckSetting> findByName(String nameTypeDeck) {
        return repository.findByName(nameTypeDeck)
                .map(SettingDeckMapper::toDomain);
    }
}
