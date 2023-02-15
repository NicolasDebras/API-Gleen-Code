package com.main.functional.model;


import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Value
@Builder
public class User {

    @Builder.Default
    UUID id = UUID.randomUUID();

    @With @Builder.Default int token = 4;

    String username;
}
