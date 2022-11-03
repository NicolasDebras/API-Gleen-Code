package com.main.gleen.infrastructure.adapter.mongodb.entity;

import com.main.gleen.domain.model.Rarity;
import com.main.gleen.domain.model.Speciality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Builder

public class HeroEntity {


    @Id
    @Getter @Setter
    private final String id;
    @Getter @Setter
    private final String name;
    @Getter @Setter
    private final int Pv;
    @Getter @Setter
    private final int xp;
    @Getter @Setter
    private final int power;
    @Getter @Setter
    private final int armor;
    @Getter @Setter
    private final Speciality speciality;
    @Getter @Setter
    private final int lvl = 0;
    @Getter @Setter
    private final Rarity rarity;
}
