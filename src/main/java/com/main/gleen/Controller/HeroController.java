package com.main.gleen.Controller;

import com.main.gleen.model.Hero;
import com.main.gleen.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeroController {

    @Autowired
    HeroService heroService;

    @GetMapping("/hero/{name}")
    public Hero show(@PathVariable String name){
        Hero res = heroService.findItemByName(name);
        return res;
    }

    @GetMapping("/hero/id/{id}")
    public Hero RoutebyID(@PathVariable String id){
        return heroService.findItembyID(id);
    }

}
