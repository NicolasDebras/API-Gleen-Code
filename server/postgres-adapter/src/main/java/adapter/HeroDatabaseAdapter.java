package adapter;

import com.main.ApplicationError;
import com.main.functional.model.Hero;
import com.main.ports.server.HeroPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.List;
import java.util.UUID;

public class HeroDatabaseAdapter implements HeroPersistenceSpi {
    @Override
    public Option<Hero> findByRarityDraw(UUID idRarity) {
        return null;
    }

    @Override
    public Option<List<Hero>> findAll() {
        return null;
    }

    @Override
    public Either<ApplicationError, Hero> save(Hero o) {
        return null;
    }

    @Override
    public Option<Hero> findById(UUID uuid) {
        return null;
    }
}
