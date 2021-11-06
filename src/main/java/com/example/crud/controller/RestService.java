package com.example.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    public static String backServerAddress;
    @Autowired
    public RestService(Environment env) {
        backServerAddress = env.getProperty("backServer.Address");
    }

    public static ResponseEntity<String> getJSON(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> respEntity = new RestTemplate().exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("[RestService] getJSON: "+url+" [HTTP "+respEntity.getStatusCodeValue()+"]");
        return respEntity;
    }

    public static ResponseEntity<String> postJSON(String url, String postData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(postData, headers);
        ResponseEntity<String> respEntity = new RestTemplate().exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println("[RestService] postJSON: "+url+" [HTTP "+respEntity.getStatusCodeValue()+"]");
        return respEntity;
    }
}
