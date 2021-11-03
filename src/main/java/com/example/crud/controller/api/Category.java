package com.example.crud.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/")
public class Category {


    @GetMapping("/category/{id}")
    public ResponseEntity<String> getCheckItem(@PathVariable long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> respEntity = new RestTemplate().exchange("http://192.168.1.38:8080/category/"+id, HttpMethod.GET, entity, String.class);
        return respEntity;
    }

}
