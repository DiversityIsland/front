package com.example.front.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import static com.example.front.controller.RestService.*;
import static com.example.front.controller.api.Constants.BACK_SERVER_ADDRESS;


@RestController
@RequestMapping("/")
public class Favorites {

    @GetMapping("/api/favorites")
    public ResponseEntity<String> favoritesPopular() {
        return getJSON(BACK_SERVER_ADDRESS + "/api/item/popular/");
    }

    @GetMapping("/favorites")
    public ResponseEntity<String> favoritesPopularPost() {
        //todo тут user1 это заглушка
        String username = "user1";
        return postJSON(BACK_SERVER_ADDRESS + "/favorites/", username);
    }

    @GetMapping("/api/favorites/existsItem/{id}")
    public ResponseEntity<String> favoritesExists(@PathVariable long id) {
        return getJSON(BACK_SERVER_ADDRESS + "/api/favorites/existsItem/" + id);
    }

    @GetMapping("/api/favorites/items")
    public ResponseEntity<String> favoritesItems() {
        return getJSON(BACK_SERVER_ADDRESS + "/api/favorites/items/");
    }

    @GetMapping("/api/favorites/shops")
    public ResponseEntity<String> favoritesShops() {
        return getJSON(BACK_SERVER_ADDRESS + "/api/favorites/shops/");
    }

}
