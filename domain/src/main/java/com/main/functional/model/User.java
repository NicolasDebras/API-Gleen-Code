package com.main.functional.model;


import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
public class User {
    String username;
    @With
    @Builder.Default
    int token = 4;

}
