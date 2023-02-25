package entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "level")
public class LevelEntity {
    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(unique = true)
    private int level;

    private Double exp;
}
