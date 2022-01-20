package com.example.front.controller.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.front.controller.RestService.*;
import static com.example.front.controller.api.Constants.BACK_SERVER_ADDRESS;


@RestController
@RequestMapping("/api/admin")
public class Admin {

    @GetMapping("/allshops")
    public ResponseEntity<String> allModeratedShops() {
        return getJSON(BACK_SERVER_ADDRESS + "/api/admin/allshops/");
    }

    @GetMapping("/alladdresses")
    public ResponseEntity<String> allAddresses() {
        return getJSON(BACK_SERVER_ADDRESS + "/api/admin/alladdresses/");
    }

    @GetMapping("/allcategories")
    public ResponseEntity<String> allCategories() {
        return getJSON(BACK_SERVER_ADDRESS + "/api/admin/allcategories");
    }

    @GetMapping("/allcities")
    public ResponseEntity<String> allCities() {
        return getJSON(BACK_SERVER_ADDRESS + "/api/admin/allcities/");
    }

    @GetMapping("/allcountries")
    public ResponseEntity<String> allCountries() {
        return getJSON(BACK_SERVER_ADDRESS + "/api/admin/allcountries/");
    }

    @GetMapping("/allitems")
    public ResponseEntity<String> allItems() {
        return getJSON(BACK_SERVER_ADDRESS + "/api/admin/allitems/");
    }

    @GetMapping("/allusers")
    public ResponseEntity<String> allUsers() {
        return getJSON(BACK_SERVER_ADDRESS + "/api/admin/allusers/");
    }

    @GetMapping("/allroles")
    public ResponseEntity<String> allRoles() {
        return getJSON(BACK_SERVER_ADDRESS + "/api/admin/allroles");
    }

    @GetMapping("/shop/{id}/allitems")
    public ResponseEntity<String> allItemsByShop(@PathVariable("id") String id) {
        return getJSON(BACK_SERVER_ADDRESS + "/api/admin/shop/" + id + "/allitems");
    }
}