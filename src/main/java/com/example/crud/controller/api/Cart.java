package com.example.crud.controller.api;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.crud.controller.RestService.*;


@RestController
@RequestMapping("/")
public class Cart {

    @GetMapping("/cart")
    public ResponseEntity<String> cart() {
        return getJSON(backServerAddress+"/cart/");
    }

}
