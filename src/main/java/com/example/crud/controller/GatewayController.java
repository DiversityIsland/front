package com.example.crud.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.example.crud.controller.RestService.backServerAddress;
import static com.example.crud.controller.RestService.getJSON;

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

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/user")
    public String userPage()  {
        return "home";
    }



    @RestController
    @RequestMapping("/")
    public class Shops {

        @GetMapping("/shops")
        public ResponseEntity<String> allModeratedShops() {
            return getJSON(backServerAddress+"/shops/");
        }

    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/feedback")
    public String feedbackPage()  {
        return "feedback";
    }

}






