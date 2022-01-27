package com.example.front.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.front.controller.RestService.*;
import static com.example.front.controller.api.Constants.BACK_SERVER_ADDRESS;


@RestController
@RequestMapping("/")
public class Category {

    @PostMapping("/api/category/add")
    public ResponseEntity<String> add(@RequestBody String category) {
        return postJSON(BACK_SERVER_ADDRESS + "/api/category/add", category);
    }

    @PutMapping("/api/category/save")
    public ResponseEntity<String> save(@RequestBody String category) {
        return putJSON(BACK_SERVER_ADDRESS + "/api/category/save", category);
    }

    @DeleteMapping("/api/category/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return deleteJSON(BACK_SERVER_ADDRESS + "/api/category/" + id);
    }

    @GetMapping("/api/category/{id}")
    public ResponseEntity<String> categoryApiId(@PathVariable long id) {
        return getJSON(BACK_SERVER_ADDRESS + "/api/category/" + id);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<String> categoryId(@PathVariable long id) {
        return getJSON(BACK_SERVER_ADDRESS + "/category/" + id);
    }
}
