package repository;

import entity.SpecialityEntity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@Transactional(propagation = MANDATORY)
public interface SpecialityRepository extends JpaRepository<SpecialityEntity, UUID> {

    Option<SpecialityEntity> findByName(String name);
}
