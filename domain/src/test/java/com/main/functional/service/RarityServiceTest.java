package com.main.functional.service;

import com.main.functional.model.Rarity;
import com.main.port.server.RarityPersistanceSpi;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RarityServiceTest {

    @InjectMocks
    private RarityService service;

    @Mock
    private RarityPersistanceSpi spi;

    @Test
    @DisplayName("Get rarity is good")
    void should_get_rarity() {
        val rarityName = "Common";
        val rarity = Rarity.builder().name(rarityName)
                .build();
        when(spi.findByName(rarityName)).thenReturn(Optional.of(rarity));
        val actual = service.find(rarityName);
        assertThat(actual).isEqualTo(rarity);
    }


    @Test
    @DisplayName("Get rarity is bad")
    void should_not_get_rarity() {
        val rarityName = "Common";
        when(spi.findByName(rarityName)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> service.find(rarityName));
    }

}