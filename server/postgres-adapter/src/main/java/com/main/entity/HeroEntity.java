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
@Table(name = "hero")
public class HeroEntity {

    @Id @Include private UUID id;

    @Column(unique = true)
    private String name;

    @OneToOne(cascade = ALL)
    private RarityEntity rarity;

    @OneToOne(cascade = ALL)
    private SpecialityEntity speciality;

}
