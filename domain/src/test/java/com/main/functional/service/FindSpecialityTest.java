package com.main.functional.service;

import com.main.functional.model.Speciality;
import com.main.port.server.SpecialityPersistanceSpi;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FindSpecialityTest {

    @Mock
    private SpecialityPersistanceSpi spi;

    @InjectMocks
    private FindSpeciality speciality;

    @Test
    @DisplayName("Good found Speciality")
    void should_find_speciality() {
        val specialityName = "Wizard";
        val specialityFound = Speciality.builder()
                .name(specialityName)
                .defense(10)
                .attack(10)
                .health(10)
                .build();
        when(spi.findByName(specialityName)).thenReturn(Optional.of(specialityFound));
        val actual = speciality.find(specialityName);
        assertEquals(specialityFound, actual);
    }


    @Test
    @DisplayName("Bad found Speciality")
    void should_not_find_speciality() {
        val specialityName = "Wizard";
        when(spi.findByName(specialityName)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> speciality.find(specialityName));
    }
}