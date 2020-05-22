package com.consearch.restservice.controllers;

import com.consearch.restservice.models.Review;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.amazonaws.services.transcribe.model.BadRequestException;

import com.consearch.restservice.handlers.ReviewHandler;

import java.util.List;

class ReviewRequest {
    private String artist_Name;
    private String eventTimestamp;
    private String review_text;
    public String getArtist_Name() {
        return artist_Name;
    }
    public String getEventTimestamp() {
        return eventTimestamp;
    }
    public String getReview_text() {
        return review_text;
    }
    public void setArtist_Name(String artist_Name) {
        this.artist_Name = artist_Name;
    }
    public void setEventTimestamp(String eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }
    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }

}

@RestController
@RequestMapping("reviews")
public class ReviewController {
    private ReviewHandler reviewHandler = new ReviewHandler();

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Review submitReview(@RequestBody ReviewRequest reviewRequest) throws Exception {
        return reviewHandler.submitReview(
                reviewRequest.getArtist_Name().toLowerCase(),
                reviewRequest.getEventTimestamp(),
                reviewRequest.getReview_text());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Review> listReviews(@RequestParam(value = "artist_Name") String artist_Name,
                                    @RequestParam(value = "eventTimestamp") String eventTimestamp) throws BadRequestException {
        return reviewHandler.listReviews(artist_Name.toLowerCase(), eventTimestamp);
    }
}
