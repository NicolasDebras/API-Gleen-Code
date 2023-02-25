package com.main.functional.service;

import com.main.ApplicationError;
import com.main.functional.model.Hero;
import com.main.ports.server.HeroPersistenceSpi;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListHeroServiceTest {

    @InjectMocks
    private ListHeroService listHeroService;

    @Mock
    private HeroPersistenceSpi heroPersistenceSpi;

    @Test
    @DisplayName("Should list all heroes")
    void shouldListAllHeroes() {
        val list = List.of(
                Hero.builder().id(UUID.randomUUID()).name("Hero 1").build(),
                Hero.builder().id(UUID.randomUUID()).name("Hero 2").build(),
                Hero.builder().id(UUID.randomUUID()).name("Hero 3").build()
        );

        when(heroPersistenceSpi.findAll()).thenReturn(Option.of(list));
        val actual = listHeroService.list();
        assertThat(actual).isRight();
        assertThat(actual).containsOnRight(list);
    }

    @Test
    @DisplayName("Should list Hero but error is list hero")
    void shouldListHeroButIsEmpty() {
        when(heroPersistenceSpi.findAll()).thenReturn(Option.none());
        val actual = listHeroService.list();
        assertThat(actual).isLeft();
        assertThat(actual).containsOnLeft(new ApplicationError("Not possible to list heroes", null, null, null));
    }

    @Test
    @DisplayName("Should list Hero but hero list is empty")
    void shouldListHeroButHeroListIsEmpty() {
        when(heroPersistenceSpi.findAll()).thenReturn(Option.of(List.of()));
        val actual = listHeroService.list();
        assertThat(actual).isLeft();
        assertThat(actual).containsOnLeft(new ApplicationError("Not hero created", null, null, null));
    }







}