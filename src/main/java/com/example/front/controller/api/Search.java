package com.example.front.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.front.controller.RestService.getJSON;
import static com.example.front.controller.api.Constants.BACK_SERVER_ADDRESS;

@RestController
@RequestMapping("/")
public class Search {

    @GetMapping("/search/{searchWords}")
    public ResponseEntity<String> searchItemByName(@PathVariable String searchWords) {
        return getJSON(BACK_SERVER_ADDRESS + "/search/" + searchWords);
    }
}
