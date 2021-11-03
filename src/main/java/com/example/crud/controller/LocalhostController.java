package com.example.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.crud.model.User;
import com.example.crud.service.RoleService;
import com.example.crud.service.UserService;


@Controller
@RequestMapping("/")
public class LocalhostController {
    final UserService service;
    final RoleService roleService;

    @Autowired
    public LocalhostController(UserService service, RoleService roleService) {
        this.service = service;
        this.roleService = roleService;
    }

    @GetMapping("login")
    public String loginPage(@ModelAttribute("user") User user)
    {
        return "login";
    }

    @GetMapping("noauth")
    public String logoutBasicAuth()
    {
        return "noauth";
    }

}
