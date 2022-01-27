package com.example.front.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.front.controller.RestService.*;
import static com.example.front.controller.api.Constants.BACK_SERVER_ADDRESS;

@RestController
@RequestMapping("/api/address")
public class Address {

    @GetMapping("/{id}")
    public ResponseEntity<String> get(@PathVariable String id) {
        return getJSON(BACK_SERVER_ADDRESS + "/api/address/" + id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody String address) {
        return postJSON(BACK_SERVER_ADDRESS + "/api/address/add", address);
    }

    @PutMapping("/save")
    public ResponseEntity<String> save(@RequestBody String address) {
        return putJSON(BACK_SERVER_ADDRESS + "/api/address/save", address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return deleteJSON(BACK_SERVER_ADDRESS + "/api/address/" + id);
    }
}
