package com.main.mongodb.entity;


import lombok.Builder;
import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("hero")
@Builder
@Value
public class UserEntity {
    String username;
}
