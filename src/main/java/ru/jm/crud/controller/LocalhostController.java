package ru.jm.crud.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import ru.jm.crud.model.User;
import ru.jm.crud.service.RoleService;
import ru.jm.crud.service.UserService;

import java.security.Principal;

import static ru.jm.crud.controller.Utils.*;

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

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("user")
    public String userPage(Principal pr, Authentication authentication, Model model) {
        model.addAttribute("principal", getPrincipal(pr, authentication, service));
        model.addAttribute("user", getPrincipal(pr, authentication, service));
        return "user";
    }

    @Secured({"ROLE_ADMIN","ROLE_USER","ROLE_GUEST"})
    @GetMapping("guest")
    public String guestPage(Principal pr, Authentication authentication, Model model) {
        model.addAttribute("principal", getPrincipal(pr, authentication, service));
        model.addAttribute("user", getPrincipal(pr, authentication, service));
        return "guest";
    }
}
