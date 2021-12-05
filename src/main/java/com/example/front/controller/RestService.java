package com.example.front.controller;

import com.example.front.Clog;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {


    private RestService() {
    }

    public static ResponseEntity<String> getJSON(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> respEntity = new ResponseEntity<>("", HttpStatus.SERVICE_UNAVAILABLE);
        try {
            respEntity = new RestTemplate().exchange(url, HttpMethod.GET, entity, String.class);
        } catch (ResourceAccessException e) {
            Clog.err.log(e.toString());
        }

        Clog.status_minor.log(String.format("[RestService] getJSON: %s [HTTP %s]", url, respEntity.getStatusCodeValue()));
        return respEntity;
    }

    public static ResponseEntity<String> postJSON(String url, String postData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(postData, headers);

        ResponseEntity<String> respEntity = new ResponseEntity<>("", HttpStatus.SERVICE_UNAVAILABLE);
        try {
            respEntity = new RestTemplate().exchange(url, HttpMethod.POST, entity, String.class);
        } catch (ResourceAccessException e) {
            Clog.err.log(e.toString());
        }

        Clog.status_minor.log(String.format("[RestService] postJSON: %s [HTTP %s]", url, respEntity.getStatusCodeValue()));
        return respEntity;
    }
}
