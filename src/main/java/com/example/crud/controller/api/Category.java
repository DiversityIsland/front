package com.example.crud.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.crud.controller.Utils.getJSON;


@RestController
@RequestMapping("/")
public class Category {


    @GetMapping("/api/category")
    public ResponseEntity<String> category() {
        return getJSON("http://192.168.1.38:8080/api/category/");
    }

    @GetMapping("/api/category/{id}")
    public ResponseEntity<String> categoryApiId(@PathVariable long id) {
        return getJSON("http://192.168.1.38:8080/api/category/"+id);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<String> categoryId(@PathVariable long id) {
        return getJSON("http://192.168.1.38:8080/category/"+id);
    }

}
