package com.main.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "historical_fight")
public class HistoricalFightEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    private CardEntity winner;
    @OneToOne(cascade = CascadeType.ALL)
    private CardEntity loser;

    @Column(name = "fight_date")
    private LocalDateTime date;
}
