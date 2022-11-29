package com.main.gleen.service;

import com.main.gleen.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroService
{
    @Autowired
    private HeroRepository heroRepository;

    public Hero findItemByName(String name) {
        return heroRepository.findItemByName(name);
    }
    public Hero findItembyID(String id) {
        return heroRepository.findItembyID(id);
    }

    public List<Hero> findAll() {
        return null;
    }
}
