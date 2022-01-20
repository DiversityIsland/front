package com.example.front.controller.api.moderator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.front.controller.RestService.*;
import static com.example.front.controller.api.Constants.BACK_SERVER_ADDRESS;

@RestController
@RequestMapping("/moderate/items")
public class ModeratorItems {

    @GetMapping
    public ResponseEntity<String> getUnmoderatedItems() {
        return getJSON(BACK_SERVER_ADDRESS + "/moderator/api/items/getUnmoderatedItems");
    }

    @GetMapping("/getOneUnmoderatedItem/{id}")
    public ResponseEntity<String> getUnmoderatedItem(@PathVariable Long id) {
        return getJSON(BACK_SERVER_ADDRESS + "/moderator/api/items/getOneUnmoderatedItem/" + id);
    }

    @GetMapping("/getUnmoderatedItemsCount")
    public ResponseEntity<String> getUnmoderatedItemsCount() {
        return getJSON(BACK_SERVER_ADDRESS + "/moderator/api/items/getUnmoderatedItemsCount");
    }

    @PutMapping("/editItem")
    public ResponseEntity<String> updateItem(@RequestBody String data) {
        return putJSON(BACK_SERVER_ADDRESS + "/moderator/api/items/editItem", data);
    }

}
