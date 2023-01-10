package com.main.mongodb.entity;

import com.main.domain.functionnal.model.Rarity;
import com.main.domain.functionnal.model.Speciality;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("hero")
@Builder
@Value
public class HeroEntity {
    @Id
    private UUID id;

    private String name;
    private int Pv;
    private int xp;
    private int power;
    private int armor;

    private Speciality speciality;
    private int lvl = 0;
    private Rarity rarity;
    private int experiencePoint;
}
