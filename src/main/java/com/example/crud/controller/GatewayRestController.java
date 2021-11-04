package com.example.crud.controller;

import com.example.crud.model.User;
import com.example.crud.service.UserService;
import org.springframework.http.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;
import com.example.crud.model.HTTPRequest;
import com.example.crud.model.UserDTO;
import com.example.crud.service.RoleService;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;



@RestController
@RequestMapping("/")
public class GatewayRestController {
    final UserService service;
    final RoleService roleService;

    @Autowired
    public GatewayRestController(UserService service , RoleService roleService) {
        this.service = service;
        this.roleService = roleService;
    }


//    String cookies;
//    @Secured({"ROLE_ADMIN","ROLE_USER"})
//    @GetMapping("/user")
//    public String userPage(Principal pr, Authentication authentication, Model model) throws URISyntaxException {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set(HttpHeaders.COOKIE, cookies);
//
//        HttpEntity<String> entity = new HttpEntity<>("", headers);
//        ResponseEntity<String> respEntity = new RestTemplate().exchange("http://localhost:8080", HttpMethod.GET, entity, String.class);
//        String tmpCookies = respEntity.getHeaders().getFirst("set-cookie");
//        if (tmpCookies != null) cookies = tmpCookies;
//        return respEntity.getBody();
//    }



//    @PostMapping("/proxy")
//    String proxy(@RequestBody HTTPRequest request) {
//        HttpMethod httpMethod =
//                (Objects.equals(request.method, "GET"))? HttpMethod.GET :
//                        (Objects.equals(request.method, "PUT"))? HttpMethod.PUT :
//                                (Objects.equals(request.method, "PATCH"))? HttpMethod.PATCH:
//                                        (Objects.equals(request.method, "DELETE"))? HttpMethod.DELETE : HttpMethod.POST;
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set(HttpHeaders.COOKIE, cookies);
//
//        HttpEntity<String> entity = new HttpEntity<>(request.postData, headers);
//        ResponseEntity<String> respEntity = new RestTemplate().exchange(request.url, httpMethod, entity, String.class);
//
//        String tmpCookies = respEntity.getHeaders().getFirst("set-cookie");
//        if (tmpCookies != null) cookies = tmpCookies;
//
//        return respEntity.getBody();
//    }
}






