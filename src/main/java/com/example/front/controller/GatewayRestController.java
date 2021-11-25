package com.example.front.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.front.controller.RestService.backServerAddress;
import static com.example.front.controller.RestService.getJSON;


@RestController
@RequestMapping("/")
public class GatewayRestController {

    @GetMapping("/shops")
    public ResponseEntity<String> allModeratedShops() {
        return getJSON(backServerAddress+"/shops/");
    }

}






