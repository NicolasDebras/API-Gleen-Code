package com.main.functional.service;

import com.main.functional.model.Fighter;
import com.main.ports.server.CardPersistenceSpi;
import com.main.ports.server.HistoricalFightPersistenceSpi;
import com.main.ports.server.UserAccountPersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;


@ExtendWith(MockitoExtension.class)
class FighterServiceTest {

    @Mock
    private  CardPersistenceSpi cardPersistenceSpi;

    @Mock
    private  UserAccountPersistenceSpi userAccountPersistenceSpi;

    @Mock
    private HistoricalFightPersistenceSpi historicalFightPersistenceSpi;

    @InjectMocks
    private  FighterService fighterService;

    @Test
    void should_create_fight() {
        //TODO

        val  idUser = UUID.randomUUID();
        val opponent = UUID.randomUUID();
        val idDefense = UUID.randomUUID();
        val card 
        val fight = Fighter.builder()
                .defenseCard(idDefense)
                .opponent(opponent)
                .build();


    }




}