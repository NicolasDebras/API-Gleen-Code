package com.main.resource;

import com.main.ports.client.FightCardApi;
import com.main.ports.client.FindHistoricalFightCardApi;
import com.main.dto.FightDto;
import lombok.RequiredArgsConstructor;
import com.main.dto.mapper.CardMapper;
import com.main.dto.mapper.FightMapper;
import com.main.dto.mapper.HistoricalListFighterMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/fight")
public class FightRessource {

    private final FightCardApi fightCardApi;

    private final FindHistoricalFightCardApi historicalFightApi;

    @PostMapping
    public ResponseEntity<Object> fight(@RequestBody FightDto fightDto) {
        return fightCardApi
                .fightCard(FightMapper.toDomain(fightDto))
                .map(CardMapper::toDto)
                .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
    }

    @GetMapping(path = "/{idFight}")
    public ResponseEntity<Object> getFight(@PathVariable UUID idFight) {
        return historicalFightApi
                .find(idFight)
                .map(HistoricalListFighterMapper::toDto)
                .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
    }
}
