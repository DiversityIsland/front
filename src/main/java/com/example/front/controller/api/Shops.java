package com.example.front.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.front.controller.RestService.*;
import static com.example.front.controller.api.Constants.BACK_SERVER_ADDRESS;


@RestController
@RequestMapping("/api/shop")
public class Shops {

    @GetMapping("/{id}")
    public ResponseEntity<String> get(@PathVariable String id) {
        return getJSON(BACK_SERVER_ADDRESS + "/api/shop/" + id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody String shop) {
        return postJSON(BACK_SERVER_ADDRESS + "/api/item/add", shop);
    }

    @PutMapping("/save")
    public ResponseEntity<String> save(@RequestBody String shop) {
        return putJSON(BACK_SERVER_ADDRESS + "/api/item/save", shop);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return deleteJSON(BACK_SERVER_ADDRESS + "/api/shop/" + id);
    }
}
