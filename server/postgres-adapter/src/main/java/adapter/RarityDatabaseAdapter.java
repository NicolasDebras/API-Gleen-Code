package adapter;

import com.main.ApplicationError;
import com.main.functional.model.Rarity;
import com.main.ports.server.RarityPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;

public class RarityDatabaseAdapter implements RarityPersistenceSpi {
    @Override
    public Either<ApplicationError, Rarity> save(Rarity o) {
        return null;
    }

    @Override
    public Option<Rarity> findById(String s) {
        return null;
    }

    @Override
    public Option<Rarity> findByName(String name) {
        return null;
    }
}
