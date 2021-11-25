package com.example.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class LocalhostController {


    //basicAuth() unlogin page
    @GetMapping("noauth")
    public String logoutBasicAuth()
    {
        return "noauth";
    }

}
