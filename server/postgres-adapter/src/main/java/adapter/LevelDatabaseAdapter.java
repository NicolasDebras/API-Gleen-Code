package adapter;

import com.main.ApplicationError;
import com.main.functional.model.Level;
import com.main.ports.server.LevelPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.LevelRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LevelDatabaseAdapter implements LevelPersistenceSpi {

    private final LevelRepository repository;

    @Override
    public Option<Level> findByLevel(int level) {
        return repository.findByLevel(level)
                .map(mapper.LevelMapper::toDomain);
    }

    @Override
    public Either<ApplicationError, Level> save(Level o) {
        return null;
    }

    @Override
    public Option<Level> findById(UUID uuid) {
        return null;
    }
}
