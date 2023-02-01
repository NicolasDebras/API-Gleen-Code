package com.main.mongodb.entity;


import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("user")
@Builder
@Value
public class UserEntity {

    @Id
    private UUID id;
    private String username;

    private int jeton;


}
