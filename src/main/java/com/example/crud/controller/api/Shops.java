package com.example.crud.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.crud.controller.RestService.*;


@RestController
@RequestMapping("/")
public class Shops {

    @GetMapping("/shops")
    public ResponseEntity<String> placeholder() {
        return getJSON(backServerAddress+"/shops/");
    }

}
