package adapter;

import com.main.ApplicationError;
import com.main.functional.model.Level;
import com.main.ports.server.LevelPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.UUID;

public class LevelDatabaseAdapter implements LevelPersistenceSpi {
    @Override
    public Option<Level> findByLevel(int level) {
        return null;
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
