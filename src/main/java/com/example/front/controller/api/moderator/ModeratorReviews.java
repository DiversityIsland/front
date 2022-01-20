package com.example.front.controller.api.moderator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.front.controller.RestService.*;
import static com.example.front.controller.api.Constants.BACK_SERVER_ADDRESS;

@RestController
@RequestMapping("/moderate/reviews")
public class ModeratorReviews {

    @GetMapping
    public ResponseEntity<String> getUnmoderatedReviews() {
        return getJSON(BACK_SERVER_ADDRESS + "/moderator/api/reviews/getUnmoderatedReviews");
    }

    @GetMapping("/getOneUnmoderatedReview/{id}")
    public ResponseEntity<String> getUnmoderatedReview(@PathVariable Long id) {
        return getJSON(BACK_SERVER_ADDRESS + "/moderator/api/reviews/getOneUnmoderatedReview/" + id);
    }

    @GetMapping("/getUnmoderatedReviewsCount")
    public ResponseEntity<String> getUnmoderatedReviewsCount() {
        return getJSON(BACK_SERVER_ADDRESS + "/moderator/api/reviews/getUnmoderatedReviewsCount");
    }

    @PutMapping("/editReview")
    public ResponseEntity<String> updateReview(@RequestBody String data) {
        return putJSON(BACK_SERVER_ADDRESS + "/moderator/api/reviews/editReview", data);
    }

}
