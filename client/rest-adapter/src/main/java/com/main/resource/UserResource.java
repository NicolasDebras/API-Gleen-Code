package com.main.resource;

import com.main.ports.client.DeckOpenApi;
import com.main.ports.client.FindDeckUserApi;
import com.main.ports.client.UserAccountCreatorApi;
import com.main.dto.OpenPackDto;
import com.main.dto.UserCreationDto;
import lombok.RequiredArgsConstructor;
import com.main.mapper.PackMapper;
import com.main.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserResource {

    private final UserAccountCreatorApi userAccountCreatorApi;

    private final FindDeckUserApi findDeckUserApi;

    private final DeckOpenApi deckOpenApi;

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserCreationDto userCreationDto) {
        return userAccountCreatorApi
                .create(UserMapper.toDomain(userCreationDto))
                .map(UserMapper::toDto)
                .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
    }

    @GetMapping(path = "/{idUser}")
    public ResponseEntity<Object> getUser(@PathVariable UUID idUser) {
        return findDeckUserApi
                .find(idUser)
                .map(PackMapper::toDto)
                .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
    }

    @PostMapping(path = "/open-pack")
    public ResponseEntity<Object> openPack(@RequestBody OpenPackDto openPackDto) {
        return deckOpenApi
                .create(PackMapper.toDomain(openPackDto))
                .map(PackMapper::toDto)
                .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
    }


}
