package com.main.rest.resource;

import com.main.domain.ports.client.HeroCreatorApi;
import com.main.rest.dto.HeroCreationDto;
import com.main.rest.dto.HeroDto;
import com.main.rest.mapper.HeroDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.main.rest.mapper.HeroDtoMapper.heroCreationToDomain;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/hero")
public class HeroRessource {

    private final HeroCreatorApi heroCreatorApi;


    @PostMapping
    public ResponseEntity<Object> createHero(@RequestBody HeroDto dto){
        return heroCreatorApi
                .create(heroCreationToDomain(dto))
                .map(HeroDtoMapper::toDto)
                .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
    }
}
