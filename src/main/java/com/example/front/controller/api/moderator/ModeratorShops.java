package com.example.front.controller.api.moderator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.front.controller.RestService.*;
import static com.example.front.controller.api.Constants.BACK_SERVER_ADDRESS;

@RestController
@RequestMapping("/moderate/shops")
public class ModeratorShops {

    @GetMapping
    public ResponseEntity<String> getUnmoderatedShops() {
        return getJSON(BACK_SERVER_ADDRESS + "/moderator/api/shops/getUnmoderatedShops");
    }

    @GetMapping("/getOneUnmoderatedShop/{id}")
    public ResponseEntity<String> getUnmoderatedShop(@PathVariable Long id) {
        return getJSON(BACK_SERVER_ADDRESS + "/moderator/api/shops/getOneUnmoderatedShop/" + id);
    }

    @GetMapping("/getUnmoderatedShopsCount")
    public ResponseEntity<String> getUnmoderatedShopsCount() {
        return getJSON(BACK_SERVER_ADDRESS + "/moderator/api/shops/getUnmoderatedShopsCount");
    }

    @PutMapping("/editShop")
    public ResponseEntity<String> updateShop(@RequestBody String data) {
        return putJSON(BACK_SERVER_ADDRESS + "/moderator/api/shops/editShop", data);
    }

}
