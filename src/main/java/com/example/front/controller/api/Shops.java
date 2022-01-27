package com.example.front.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.front.controller.RestService.*;
import static com.example.front.controller.api.Constants.BACK_SERVER_ADDRESS;


@RestController
@RequestMapping("/")
public class Shops {

    @GetMapping("/api/shop/{id}")
    public ResponseEntity<String> get(@PathVariable String id) {
        return getJSON(BACK_SERVER_ADDRESS + "/api/shop/" + id);
    }

    @PostMapping("/api/shop/add")
    public ResponseEntity<String> add(@RequestBody String shop) {
        return postJSON(BACK_SERVER_ADDRESS + "/api/item/add", shop);
    }

    @PutMapping("/api/shop/save")
    public ResponseEntity<String> save(@RequestBody String shop) {
        return putJSON(BACK_SERVER_ADDRESS + "/api/item/save", shop);
    }

    @DeleteMapping("/api/shop/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return deleteJSON(BACK_SERVER_ADDRESS + "/api/shop/" + id);
    }

    @GetMapping("/api/shops")
    public ResponseEntity<String> allModeratedShops() {
        return getJSON(BACK_SERVER_ADDRESS + "/api/shop/popular");
    }
}
