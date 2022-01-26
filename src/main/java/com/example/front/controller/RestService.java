package com.example.front.controller;

import com.example.front.Clog;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    private static final String AUTHORIZATION = "Authorization";

    private RestService() {
    }

    public static ResponseEntity<String> getJSON(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(null != SecurityContextHolder.getContext().getAuthentication()) {
            headers.set(AUTHORIZATION, (String)SecurityContextHolder.getContext().getAuthentication().getCredentials());
        }
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails());

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
        if(null != SecurityContextHolder.getContext().getAuthentication()) {
            headers.set(AUTHORIZATION, (String)SecurityContextHolder.getContext().getAuthentication().getCredentials());
        }

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

    public static ResponseEntity<String> putJSON(String url, String postData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(null != SecurityContextHolder.getContext().getAuthentication()) {
            headers.set(AUTHORIZATION, (String)SecurityContextHolder.getContext().getAuthentication().getCredentials());
        }
        HttpEntity<String> entity = new HttpEntity<>(postData, headers);

        ResponseEntity<String> respEntity = new ResponseEntity<>("", HttpStatus.SERVICE_UNAVAILABLE);
        try {
            respEntity = new RestTemplate().exchange(url, HttpMethod.PUT, entity, String.class);
        } catch (ResourceAccessException e) {
            Clog.err.log(e.toString());
        }

        Clog.status_minor.log(String.format("[RestService] putJSON: %s [HTTP %s]", url, respEntity.getStatusCodeValue()));
        return respEntity;
    }
}
