package repository;

import com.main.ApplicationError;
import com.main.functional.model.User;
import entity.UserEntity;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;
@Repository
@Transactional(propagation = MANDATORY)
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Option<UserEntity> findUserEntityById(UUID id);

    Either<ApplicationError, User> updateToken(UUID idCard);
}
