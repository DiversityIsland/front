package com.example.front.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.front.controller.RestService.*;
import static com.example.front.controller.api.Constants.BACK_SERVER_ADDRESS;


@RestController
@RequestMapping("/")
public class Category {

    @GetMapping("/api/category")
    public ResponseEntity<String> category() {
        return getJSON(BACK_SERVER_ADDRESS + "/api/category/");
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
