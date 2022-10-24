package com.main.gleen.model;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Hero")
@Builder
public class Hero {


    public enum Rarity {
        COMMUN,
        RARE,
        LEGENDARY,
    }
    public enum Speciality {
        TANK,
        ASSASSIN,
        MAGE,
    }
    @Id @Getter @Setter
    private String id;

    @Getter @Setter
    private String name;
    @Getter @Setter
    private int Pv;
    @Getter @Setter
    private int xp;
    @Getter @Setter
    private int power;
    @Getter @Setter
    private int armor;
    @Getter @Setter
    private Speciality speciality;
    @Getter @Setter
    private int lvl = 0;
    @Getter @Setter
    private Rarity rarity;

}
