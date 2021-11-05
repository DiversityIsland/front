package com.example.crud.controller;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class Utils {
    public static String backServerAddress = "http://192.168.1.38:8080";

    public static ResponseEntity<String> getJSON(String url) {
        System.out.println(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> respEntity = new RestTemplate().exchange(url, HttpMethod.GET, entity, String.class);
        return respEntity;
    }
}
