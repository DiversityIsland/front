package com.example.front.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.front.controller.RestService.*;
import static com.example.front.controller.api.Constants.BACK_SERVER_ADDRESS;

@RestController
@RequestMapping("/api/country")
public class Country {

    @GetMapping("/{id}")
    public ResponseEntity<String> get(@PathVariable String id) {
        return getJSON(BACK_SERVER_ADDRESS + "/api/country/" + id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody String country) {
        return postJSON(BACK_SERVER_ADDRESS + "/api/country/add", country);
    }

    @PutMapping("/save")
    public ResponseEntity<String> save(@RequestBody String country) {
        return putJSON(BACK_SERVER_ADDRESS + "/api/country/save", country);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return deleteJSON(BACK_SERVER_ADDRESS + "/api/country/" + id);
    }
}
