package com.example.front.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.front.controller.RestService.*;
import static com.example.front.controller.api.Constants.BACK_SERVER_ADDRESS;

@RestController
@RequestMapping("/api")
public class User {

    @GetMapping("/user/{id}")
    public ResponseEntity<String> get(@PathVariable String id) {
        return getJSON(BACK_SERVER_ADDRESS + "/api/" + id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody String user) {
        return postJSON(BACK_SERVER_ADDRESS + "/api/add", user);
    }

    @PutMapping("/save")
    public ResponseEntity<String> save(@RequestBody String user) {
        return putJSON(BACK_SERVER_ADDRESS + "/api/save", user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return deleteJSON(BACK_SERVER_ADDRESS + "/api/" + id);
    }
}
