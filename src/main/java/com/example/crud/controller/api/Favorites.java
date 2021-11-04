package com.example.crud.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api/favorites")
public class Favorites {

    @GetMapping()
    public String getCheckItem1() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> respEntity = new RestTemplate().exchange("http://192.168.1.38:8080/api/item/popular/", HttpMethod.GET, entity, String.class);
        return respEntity.getBody();
    }

    @GetMapping("/existsItem/{id}")
    public String getCheckItem(@PathVariable long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> respEntity = new RestTemplate().exchange("http://192.168.1.38:8080/api/favorites/existsItem/"+id, HttpMethod.GET, entity, String.class);
        return respEntity.getBody();
    }

    @GetMapping("/items")
    public String placeholder1() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> respEntity = new RestTemplate().exchange("http://192.168.1.38:8080/api/favorites/items/", HttpMethod.GET, entity, String.class);
        return respEntity.getBody();
    }

    @GetMapping("/shops")
    public String placeholder2() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> respEntity = new RestTemplate().exchange("http://192.168.1.38:8080/api/favorites/shops/", HttpMethod.GET, entity, String.class);
        return respEntity.getBody();
    }

}
