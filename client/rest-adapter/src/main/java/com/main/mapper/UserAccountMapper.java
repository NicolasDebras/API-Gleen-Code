package com.main.mapper;

import com.main.dto.UserCreationDto;
import com.main.dto.UserDto;
import com.main.functional.model.User;

public interface UserAccountMapper {

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
