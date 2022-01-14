package com.example.front.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.front.controller.RestService.*;
import static com.example.front.controller.api.Constants.BACK_SERVER_ADDRESS;


@RestController
@RequestMapping("/")
public class Shops {

    @GetMapping("/api/shops")
    public ResponseEntity<String> allModeratedShops() {
        return getJSON(BACK_SERVER_ADDRESS + "/api/shop/popular");
    }
}
