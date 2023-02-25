package adapter;

import com.main.ApplicationError;
import com.main.functional.model.Speciality;
import com.main.ports.server.SpecialityPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.SpecialityRepository;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class SpecialityDatabaseAdapter implements SpecialityPersistenceSpi {

    private final SpecialityRepository repository;
    @Override
    public Either<ApplicationError, Speciality> save(Speciality o) {
        return repository.save(mapper.SpecialityEntityMapper.fromDomain(o))
                .map(mapper.SpecialityEntityMapper::toDomain)
                .toEither(new ApplicationError("Error while saving speciality", null, o, null));
    }

    @Override
    public Option<Speciality> findById(String s) {
        return null;
    }

    @Override
    public Option<Speciality> findByName(String name) {
        return repository.findByName(name)
                .map(mapper.SpecialityEntityMapper::toDomain);
    }
}
