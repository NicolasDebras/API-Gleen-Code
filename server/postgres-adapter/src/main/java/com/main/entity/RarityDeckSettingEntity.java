package com.main.entity;


import lombok.*;

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
@Table(name = "rarity_deck_setting")
public class RarityDeckSettingEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    private Double percentageLucky;

    @OneToOne(cascade = ALL)
    private RarityEntity rarity;
}
