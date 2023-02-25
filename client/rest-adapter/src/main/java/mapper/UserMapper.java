package mapper;

import com.main.functional.model.User;
import dto.UserCreationDto;
import dto.UserDto;

public interface UserMapper {

    static User toDomain(UserCreationDto dto) {
        return User.builder()
                .username(dto.username())
                .build();
    }

    static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getToken());
    }
}
