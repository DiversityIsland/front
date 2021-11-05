package com.example.crud.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.crud.controller.Utils.getJSON;


@RestController
@RequestMapping("/api/favorites")
public class Favorites {

    @GetMapping()
    public ResponseEntity<String> favoritesPopular() {
        return getJSON("http://192.168.1.38:8080/api/item/popular/");
    }

    @GetMapping("/existsItem/{id}")
    public ResponseEntity<String> favoritesExists(@PathVariable long id) {
        return getJSON("http://192.168.1.38:8080/api/favorites/existsItem/"+id);
    }

    @GetMapping("/items")
    public ResponseEntity<String> favoritesItems() {
        return getJSON("http://192.168.1.38:8080/api/favorites/items/");
    }

    @GetMapping("/shops")
    public ResponseEntity<String> favoritesShops() {
        return getJSON("http://192.168.1.38:8080/api/favorites/shops/");
    }

}
