package com.main.mongodb.entity;

import com.main.domain.functionnal.model.RarityHero;
import com.main.domain.functionnal.model.SpecialityHero;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("hero")
public class HeroEntity {
    @Id
    private UUID id;
    private String name;
    private int pv;
    private int xp = 0;
    private int power = 0;
    private int armor = 0;
    private int level = 0;
    private int experiencePoint = 0;
    private SpecialityHero specialityHero;
    private RarityHero rarityHero;

    public void setId(UUID id) {
        this.id = id;
    }

    @javax.persistence.Id
    public UUID getId() {
        return id;
    }
}
