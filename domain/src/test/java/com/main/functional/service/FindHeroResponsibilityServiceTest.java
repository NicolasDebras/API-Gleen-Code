package com.main.functional.service;

import com.main.functional.model.HeroCreate;
import com.main.functional.model.HeroFind;
import com.main.port.server.HeroFindPersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static io.vavr.API.Right;
import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FindHeroResponsibilityServiceTest {

    @InjectMocks
    private FindHeroResponsibilityService service;

    @Mock
    private HeroFindPersistenceSpi spi;


    @Test
    @DisplayName("Find hero is good")
    void should_find_hero() {
        val listHero = List.of(HeroCreate.builder().build());
        val heroFind = HeroFind
                .builder()
                .offset(0)
                .limit(10)
                .build();
        when(spi.findHeroCreateByFilter(heroFind)).thenReturn(Right(listHero));
        val actual = service.findHeroByFilter(heroFind);
        assertThat(actual).containsRightSame(listHero);
        verifyNoMoreInteractions(spi);

    }



    @Test
    @DisplayName("Find hero is bad")
    void should_not_find_hero() {
        val heroFind = HeroFind
                .builder()
                .offset(1)
                .limit(10)
                .build();
        when(spi.findHeroCreateByFilter(heroFind)).thenReturn(io.vavr.API.Left(new com.main.ApplicationError("An error occurred", null, null, null)));
        val actual = service.findHeroByFilter(heroFind);
        assertThat(actual).containsLeftInstanceOf(null);
        verifyNoMoreInteractions(spi);
    }

}