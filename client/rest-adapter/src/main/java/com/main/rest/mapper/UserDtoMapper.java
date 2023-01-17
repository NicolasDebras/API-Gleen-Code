package com.main.rest.mapper;

import com.main.domain.functionnal.model.User;
import com.main.rest.dto.UserCreationDto;

public interface UserDtoMapper {

    static UserCreationDto toDo(User user){
        return new UserCreationDto(
                user.getPseudo()
        );
    }


    static User userCreationToDomain(UserCreationDto dto) {
        return User.builder()
                .pseudo(dto.username())
                .build();
    }
}
