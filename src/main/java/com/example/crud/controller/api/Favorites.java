package com.example.crud.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.crud.controller.RestService.*;


@RestController
@RequestMapping("/api/favorites")
public class Favorites {

    @GetMapping()
    public ResponseEntity<String> favoritesPopular() {
        return getJSON(backServerAddress+"/api/item/popular/");
    }

    @GetMapping("/existsItem/{id}")
    public ResponseEntity<String> favoritesExists(@PathVariable long id) {
        return getJSON(backServerAddress+"/api/favorites/existsItem/"+id);
    }

    @GetMapping("/items")
    public ResponseEntity<String> favoritesItems() {
        return getJSON(backServerAddress+"/api/favorites/items/");
    }

    @GetMapping("/shops")
    public ResponseEntity<String> favoritesShops() {
        return getJSON(backServerAddress+"/api/favorites/shops/");
    }

}
