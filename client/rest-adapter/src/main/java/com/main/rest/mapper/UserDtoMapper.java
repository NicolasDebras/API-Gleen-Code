package com.main.rest.mapper;

import com.main.domain.functionnal.model.User;
import com.main.rest.dto.UserCreationDto;
import com.main.rest.dto.UserCreationResponseDto;

public interface UserDtoMapper {

    static UserCreationResponseDto toDo(User user){
        return new UserCreationResponseDto(
                user.getId(),
                user.getPseudo(),
                user.getJeton()
        );
    }


    static User userCreationToDomain(UserCreationDto dto) {
        return User.builder()
                .pseudo(dto.username())
                .build();
    }
}
