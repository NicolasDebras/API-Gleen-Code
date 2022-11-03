package com.main.gleen.application.api.restful;

import com.main.gleen.domain.model.Hero;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class HeroControllerImpl  implements HeroController{
    @Override
    public ResponseEntity<List<Hero>> getProducts() {
        return null;
    }

    @Override
    public ResponseEntity<Void> addProduct(Hero hero) {
        return null;
    }

    @Override
    public ResponseEntity<Void> removeProduct(Hero hero) {
        return null;
    }

    @Override
    public ResponseEntity<Hero> getProductById(Integer heroId) {
        return null;
    }
}
