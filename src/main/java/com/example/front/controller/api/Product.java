package com.example.front.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.front.controller.RestService.*;
import static com.example.front.controller.api.Constants.BACK_SERVER_ADDRESS;


@RestController
@RequestMapping("/")
public class Product {

    @GetMapping("/product/{id}")
    public ResponseEntity<String> getCheckItem(@PathVariable long id) {
        return getJSON(BACK_SERVER_ADDRESS + "/product/" + id);
    }

}
