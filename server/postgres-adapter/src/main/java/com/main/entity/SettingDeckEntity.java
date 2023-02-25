package com.main.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "setting_deck")
public class SettingDeckEntity {
    @EqualsAndHashCode.Include @Id
    private UUID id;

    @Column(unique = true)
    private String nameDeckType;
    private int token;

    private int numberCard;

    @OneToMany
    private List<RarityDeckSettingEntity> rarityDeckSettings;
}
