package com.example.crud.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.crud.controller.Utils.getJSON;


@RestController
@RequestMapping("/")
public class Product {

    @GetMapping("/product/{id}")
    public ResponseEntity<String> getCheckItem(@PathVariable long id) {
        return getJSON("http://192.168.1.38:8080/product/"+id);
    }

}
