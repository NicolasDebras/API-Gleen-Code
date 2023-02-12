package com.main.functional.service;

public class PercentageHero {

    public static  int calculateValueWithPercentage(int value, Double percentage) {
        return (int) (value + (value * (percentage / 100)));
    }


}
