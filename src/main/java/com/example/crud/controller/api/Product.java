package com.example.crud.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.crud.controller.Utils.*;


@RestController
@RequestMapping("/")
public class Product {

    @GetMapping("/product/{id}")
    public ResponseEntity<String> getCheckItem(@PathVariable long id) {
        return getJSON(backServerAddress+"/product/"+id);
    }

}
