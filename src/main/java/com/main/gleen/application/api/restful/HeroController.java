package com.main.gleen.application.api.restful;

import com.main.gleen.domain.model.Hero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface HeroController {
    @GetMapping("/hero")
    ResponseEntity<List<Hero>> getProducts();

    @PostMapping("/hero")
    ResponseEntity<Void> addProduct(@RequestBody Hero hero);

    @DeleteMapping("/product")
    ResponseEntity<Void> removeProduct(@RequestBody Hero hero);

    @GetMapping("/hero/{heroId}")
    ResponseEntity<Hero> getProductById(@PathVariable Integer heroId);
}
