package entity;

import lombok.*;
import lombok.EqualsAndHashCode.Include;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "rarity")
public class RarityEntity {

    @Id @Include private UUID id;

    @Column(unique = true)
    private String name;

    private Double percentage;

}
