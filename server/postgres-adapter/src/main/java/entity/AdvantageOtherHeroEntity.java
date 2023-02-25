package entity;


import lombok.*;
import lombok.EqualsAndHashCode.Include;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "advantage_other_hero")
public class AdvantageOtherHeroEntity {

      @Id @Include private UUID id;

      private int power;

      private String name;
}
