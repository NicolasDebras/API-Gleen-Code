package com.main.mongodb.migration.utils;

import lombok.Builder;

import java.util.Random;


@Builder
public class EnumRandomizer<E extends Enum<E>> {
    private final Random rand = new Random();

    public E randomValue(Class<E> enumClass) {
        E[] values = enumClass.getEnumConstants();
        return values[rand.nextInt(values.length)];
    }

    public EnumRandomizer() {
    }
}
