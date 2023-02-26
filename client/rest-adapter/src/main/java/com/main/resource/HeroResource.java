package com.main.resource;

import com.main.mapper.AllHeroMapper;
import com.main.ports.client.HeroCreatorApi;
import com.main.ports.client.ListHeroApi;
import com.main.dto.HeroCreationDto;
import lombok.RequiredArgsConstructor;
import com.main.mapper.HeroMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/hero")
public class HeroResource {

    private final HeroCreatorApi heroCreatorApi;

    private final ListHeroApi listHeroApi;

    @PostMapping
    public ResponseEntity<Object> createHero(@RequestBody HeroCreationDto heroCreationDto) {
        return heroCreatorApi
                .create(HeroMapper.heroCreationToDomain(heroCreationDto))
                .map(HeroMapper::toDto)
                .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
    }

    @GetMapping
    public ResponseEntity<Object> listHeroes() {
        return listHeroApi
                .list()
                .map(AllHeroMapper::toDto)
                .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
    }
}
