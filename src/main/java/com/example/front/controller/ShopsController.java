package com.example.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class ShopsController {

    @GetMapping("/shops")
    public String shopsPage() {
        return "shops";
    }
}
