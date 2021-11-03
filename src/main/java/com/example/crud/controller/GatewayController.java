package com.example.crud.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class GatewayController {


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
//        //return "home";
//    }

}






