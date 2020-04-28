package com.consearch.restservice.handlers;

import com.amazonaws.services.transcribe.model.BadRequestException;

import com.consearch.restservice.dynamoDB.ReviewDynamoLayer;
import com.consearch.restservice.models.Review;

import java.util.List;

public class ReviewHandler {
    private ReviewDynamoLayer reviewDynamoLayer;

    public ReviewHandler() {
        this.reviewDynamoLayer = new ReviewDynamoLayer();
    }

    public Review submitReview(String artist_Name, String eventTimestamp, String reviewText) throws BadRequestException {
        return reviewDynamoLayer.putReview(artist_Name, eventTimestamp, reviewText);
    }

    public List<Review> listReviews(String artist_Name, String eventTimestamp) throws BadRequestException {
        return reviewDynamoLayer.getReviews(artist_Name.toLowerCase(), eventTimestamp);
    }
}