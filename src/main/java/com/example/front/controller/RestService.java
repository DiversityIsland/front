package com.example.front.controller;

import com.example.front.Clog;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RestService {
    private static RestTemplate restTemplate = new RestTemplate();
    private static HttpHeaders headers = new HttpHeaders();

    {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    private RestService() {
    }

    public static ResponseEntity<String> getJSON(String url) {

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> respEntity = new ResponseEntity<>("", HttpStatus.SERVICE_UNAVAILABLE);
        try {
            respEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        } catch (ResourceAccessException e) {
            Clog.err.log(e.toString());
        }

        Clog.status_minor.log(String.format("[RestService] getJSON: %s [HTTP %s]", url, respEntity.getStatusCodeValue()));
        return respEntity;
    }

    public static ResponseEntity<String> postJSON(String url, String postData) {

        HttpEntity<String> entity = new HttpEntity<>(postData, headers);

        ResponseEntity<String> respEntity = new ResponseEntity<>("", HttpStatus.SERVICE_UNAVAILABLE);
        try {
            respEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        } catch (ResourceAccessException e) {
            Clog.err.log(e.toString());
        }

        Clog.status_minor.log(String.format("[RestService] postJSON: %s [HTTP %s]", url, respEntity.getStatusCodeValue()));
        return respEntity;
    }

    public static ResponseEntity<String> putJSON(String url, String putData) {

        HttpEntity<String> entity = new HttpEntity<>(putData, headers);

        ResponseEntity<String> respEntity = new ResponseEntity<>("", HttpStatus.SERVICE_UNAVAILABLE);
        try {
            respEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        } catch (ResourceAccessException e) {
            Clog.err.log(e.toString());
        }

        Clog.status_minor.log(String.format("[RestService] postJSON: %s [HTTP %s]", url, respEntity.getStatusCodeValue()));
        return respEntity;
    }

    public static ResponseEntity<String> deleteJSON(String url) {

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> respEntity = new ResponseEntity<>("", HttpStatus.SERVICE_UNAVAILABLE);
        try {
            respEntity = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
        } catch (ResourceAccessException e) {
            Clog.err.log(e.toString());
        }

        Clog.status_minor.log(String.format("[RestService] postJSON: %s [HTTP %s]", url, respEntity.getStatusCodeValue()));
        return respEntity;
    }
}
