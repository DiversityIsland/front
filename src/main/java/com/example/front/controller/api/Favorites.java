package com.example.front.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import static com.example.front.controller.RestService.*;


@RestController
@RequestMapping("/")
public class Favorites {

    @GetMapping("/api/favorites")
    public ResponseEntity<String> favoritesPopular() {
        return getJSON(backServerAddress+"/api/item/popular/");
    }

    @GetMapping("/favorites")
    public ResponseEntity<String> favoritesPopularPost() {
        //todo тут user1 это заглушка
        String username = "user1";
        return postJSON(backServerAddress+"/favorites/", username);
    }

    @GetMapping("/api/favorites/existsItem/{id}")
    public ResponseEntity<String> favoritesExists(@PathVariable long id) {
        return getJSON(backServerAddress+"/api/favorites/existsItem/"+id);
    }

    @GetMapping("/api/favorites/items")
    public ResponseEntity<String> favoritesItems() {
        return getJSON(backServerAddress+"/api/favorites/items/");
    }

    @GetMapping("/api/favorites/shops")
    public ResponseEntity<String> favoritesShops() {
        return getJSON(backServerAddress+"/api/favorites/shops/");
    }

}
