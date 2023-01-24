package com.main.rest.resource;

import com.main.domain.ports.client.UserCreatorApi;
import com.main.rest.dto.UserCreationDto;
import com.main.rest.mapper.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.main.rest.mapper.UserDtoMapper.userCreationToDomain;
import static io.vavr.concurrent.Future.fold;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserRessource {

    private final UserCreatorApi userCreatorApi;

    @PostMapping
    public ResponseEntity<Object> createHero(@RequestBody UserCreationDto dto) {
        return userCreatorApi
                .create(userCreationToDomain(dto))
                .map(UserDtoMapper::toDo)
                .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
    }
}
