package com.main.entity;

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
@Table(name = "user")
public class UserEntity {
    @Id @Include private UUID id;

    @Column(unique = true)
    private String username;

    private int token;
}
