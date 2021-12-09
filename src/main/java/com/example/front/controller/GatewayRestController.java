package com.example.front.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class GatewayRestController {

    @GetMapping("/test")
    public ResponseEntity<String> restGetTest() {
        return new ResponseEntity<>("{\"testPassed\":\"true\"}", HttpStatus.OK);
    }

    @PostMapping("/test")
    public ResponseEntity<String> restPostTest(@RequestBody String postData) {
        return new ResponseEntity<>(postData, HttpStatus.OK);
    }
}






