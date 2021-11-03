package com.example.crud.controller;

import org.springframework.http.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.example.crud.model.User;
import com.example.crud.service.RoleService;
import com.example.crud.service.UserService;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Objects;

import static com.example.crud.controller.Utils.*;

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

//    String cookies;
//    @Secured({"ROLE_ADMIN","ROLE_USER"})
//    @GetMapping("user")
//    public String userPage(Principal pr, Authentication authentication, Model model) throws URISyntaxException {
//        model.addAttribute("principal", getPrincipal(pr, authentication, service));
//        model.addAttribute("user", getPrincipal(pr, authentication, service));
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set(HttpHeaders.COOKIE, cookies);
//
//        HttpEntity<String> entity = new HttpEntity<>("", headers);
//        ResponseEntity<String> respEntity = new RestTemplate().exchange("http://localhost:8080", HttpMethod.GET, entity, String.class);
//        String tmpCookies = respEntity.getHeaders().getFirst("set-cookie");
//        if (tmpCookies != null) cookies = tmpCookies;
//        System.out.println(respEntity.getBody());
//        return respEntity.getBody();
//
////        URI yahoo = new URI("http://localhost:8080");
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.setLocation(yahoo);
////        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
//    }

    @Secured({"ROLE_ADMIN","ROLE_USER","ROLE_GUEST"})
    @GetMapping("guest")
    public String guestPage(Principal pr, Authentication authentication, Model model) {
        model.addAttribute("principal", getPrincipal(pr, authentication, service));
        model.addAttribute("user", getPrincipal(pr, authentication, service));
        return "guest";
    }

    @GetMapping("test")
    public String task314() {
        return "test";
    }
}
