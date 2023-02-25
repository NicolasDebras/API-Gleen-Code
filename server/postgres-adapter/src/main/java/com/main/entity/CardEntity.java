package com.main.entity;

import lombok.*;
import lombok.EqualsAndHashCode.Include;

import javax.persistence.*;
import java.util.UUID;
import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "card")
public class CardEntity {
    @Id @Include private UUID id;

    @OneToOne(cascade = ALL)
    private LevelEntity level;

    @OneToOne(cascade = ALL)
    private UserEntity user;

    private int experience;

    @OneToOne(cascade = ALL)
    private HeroEntity hero;
}
