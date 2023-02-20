package com.main.functional.model;


import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Fighter {
    Card attackCard;
    Card defenseCard;


}
