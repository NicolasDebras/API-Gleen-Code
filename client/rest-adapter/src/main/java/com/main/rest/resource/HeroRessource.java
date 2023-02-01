package com.main.rest.resource;


import com.main.domain.ports.client.HeroCreatorApi;
import com.main.rest.dto.HeroCreationDto;
import com.main.rest.mapper.HeroDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.main.rest.mapper.HeroDtoMapper.heroCreationToDomain;
import static com.main.rest.mapper.HeroDtoMapper.toDo;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/hero")
public class HeroRessource {

    private final HeroCreatorApi heroCreatorApi;

    @PostMapping
    public ResponseEntity<Object> createHero(@RequestBody HeroCreationDto dto){
        return heroCreatorApi
                .create(heroCreationToDomain(dto))
                .map(HeroDtoMapper::toDo)
                .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
    }


}
