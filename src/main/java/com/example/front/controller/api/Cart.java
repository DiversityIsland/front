package com.example.front.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.front.controller.RestService.*;
import static com.example.front.controller.api.Constants.BACK_SERVER_ADDRESS;


@RestController
@RequestMapping("/")
public class Cart {

    @GetMapping("/cart")
    public ResponseEntity<String> cart() {
        return getJSON(BACK_SERVER_ADDRESS + "/cart/");
    }

}
