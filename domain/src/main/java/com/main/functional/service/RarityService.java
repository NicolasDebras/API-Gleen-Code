package com.main.functional.service;


import com.main.functional.model.Rarity;
import com.main.port.server.RarityPersistanceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class RarityService {


    private final RarityPersistanceSpi spi;

    public Rarity find(String name) {
        return spi.findByName(name).orElseThrow(() -> new IllegalArgumentException("Rarity not found"));
    }



}
