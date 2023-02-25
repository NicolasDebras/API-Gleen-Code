package adapter;

import com.main.ApplicationError;
import com.main.functional.model.Hero;
import com.main.ports.server.HeroPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import mapper.HeroMapper;
import org.springframework.stereotype.Service;
import repository.HeroRepository;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class HeroDatabaseAdapter implements HeroPersistenceSpi {

    private final HeroRepository repository;

    @Override
    public Option<Hero> findByRarityDraw(UUID idRarity) {
        return repository.findByRarityDraw(idRarity)
                .map(mapper.HeroMapper::toDomain);
    }

    @Override
    public Option<List<Hero>> findAll() {
        return repository.findAll()
                .map(HeroMapper::toDomainList);
    }

    @Override
    public Either<ApplicationError, Hero> save(Hero o) {
        return repository.save(HeroMapper.fromDomain(o))
                .map(HeroMapper::toDomain)
                .toEither(new ApplicationError("Error while saving hero", null, o, null));
    }

    @Override
    public Option<Hero> findById(UUID uuid) {
        return repository.findById(uuid)
                .map(HeroMapper::toDomain);
    }
}
