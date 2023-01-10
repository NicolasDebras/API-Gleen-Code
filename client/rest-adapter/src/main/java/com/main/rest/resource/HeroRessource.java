package com.main.rest.resource;


import com.main.domain.ports.client.HeroCreatorApi;
import com.main.rest.dto.HeroCreationDto;
import com.main.rest.mapper.HeroDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.main.rest.mapper.HeroDtoMapper.heroCreationToDomain;
import static com.main.rest.mapper.HeroDtoMapper.toDo;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/hero")
public class HeroRessource {

    private final HeroCreatorApi heroCreatorApi;

    @PostMapping
    public ResponseEntity<Object> createHero(@RequestBody HeroCreationDto dto){
        var hello = heroCreationToDomain(dto);
        var yesy = heroCreatorApi.create(hello);
        toDo(yesy.get());
        return null;
    }
}
