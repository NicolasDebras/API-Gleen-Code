package com.main.functional.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PercentageHero {

    public int calculateValueWithPercentage(int value, Double percentage) {
        return (int) (value + (value * percentage ));
    }


}
