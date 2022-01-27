package com.example.front.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.front.controller.RestService.*;
import static com.example.front.controller.api.Constants.BACK_SERVER_ADDRESS;

@RestController
@RequestMapping("/api/city")
public class City {

    @GetMapping("/{id}")
    public ResponseEntity<String> get(@PathVariable String id) {
        return getJSON(BACK_SERVER_ADDRESS + "/api/city/" + id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody String city) {
        return postJSON(BACK_SERVER_ADDRESS + "/api/city/add", city);
    }

    @PutMapping("/save")
    public ResponseEntity<String> save(@RequestBody String city) {
        return putJSON(BACK_SERVER_ADDRESS + "/api/city/save", city);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return deleteJSON(BACK_SERVER_ADDRESS + "/api/city/" + id);
    }
}
