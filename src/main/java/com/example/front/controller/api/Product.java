package com.example.front.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.front.controller.RestService.*;


@RestController
@RequestMapping("/")
public class Product {

    @GetMapping("/product/{id}")
    public ResponseEntity<String> getCheckItem(@PathVariable long id) {
        return getJSON(backServerAddress+"/product/"+id);
    }

}
