package com.example.front.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class GatewayController {


    @GetMapping()
    public String userPage()  {
        return "home";
    }


    @Secured({"ROLE_ADMIN"})
    @GetMapping("/admin")
    public String adminPage()  {
        return "admin";
    }


    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/feedback")
    public String feedbackPage()  {
        return "feedback";
    }

}






