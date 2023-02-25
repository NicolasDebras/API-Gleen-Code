package mapper;

import com.main.functional.model.User;
import entity.UserEntity;

public interface UserMapper {

    static User toDomain(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .token(entity.getToken())
                .build();
    }

    static UserEntity fromDomain(User domain) {
        return UserEntity.builder()
                .id(domain.getId())
                .username(domain.getUsername())
                .token(domain.getToken())
                .build();
    }
}
