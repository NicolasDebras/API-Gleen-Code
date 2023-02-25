package entity;

import lombok.*;

import javax.persistence.Entity;
import lombok.*;
import lombok.EqualsAndHashCode.Include;

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
@Table(name = "speciality")
public class SpecialityEntity {

    @Id @Include private UUID id;

    @Column(unique = true)
    private String name;
    private int health;

    private int power;

    private int armor;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AdvantageOtherHeroEntity> advantageOtherHero;


}
