package com.consearch.restservice.dynamoDB;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.xspec.S;
import com.amazonaws.services.transcribe.model.BadRequestException;
import com.consearch.restservice.models.Event;
import com.consearch.restservice.models.Review;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class ReviewDynamoLayer {
    private static final Regions REGION = Regions.US_EAST_1;

    private DynamoDBMapper dynamoDBMapper;

    public ReviewDynamoLayer() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(REGION).build();
        dynamoDBMapper = new DynamoDBMapper(client);
    }

    public Review putReview(String artist_Name, String eventTimestamp, String reviewText) throws BadRequestException {
        Review artistToSave = generatePutReviewObject(artist_Name, eventTimestamp, reviewText);
        try {
            dynamoDBMapper.save(artistToSave);
            return artistToSave;
        } catch (Error err) {
            throw new BadRequestException("bad request");
        }
    }

    public PaginatedQueryList<Review> getReviews(String artist_Name, String eventTimestamp) throws BadRequestException {
        Review review = generateReviewObject(artist_Name, eventTimestamp);
        DynamoDBQueryExpression<Review> queryExpression = new DynamoDBQueryExpression<Review>()
                .withHashKeyValues(review);

        try {
            return dynamoDBMapper.query(Review.class, queryExpression);
        } catch (Error err) {
            throw new BadRequestException("bad request");
        }
    }

    private Review generateReviewObject(String artist_Name, String eventTimestamp) {
        Review review = new Review();
        review.setEvent_Id(getEventId(artist_Name, eventTimestamp));
        return review;
    }

    private Review generatePutReviewObject(String artist_Name, String eventTimestamp, String reviewText) {
        Review putItem = new Review();
        putItem.setEvent_Id(getEventId(artist_Name, eventTimestamp));
        putItem.setReview_text(reviewText);
        putItem.setTime_stamp(Objects.toString(System.currentTimeMillis()));
        return putItem;
    }

    private static String getEventId(String artist_Name, String eventTimestamp) {
        return getArtistId(artist_Name) + '_' + eventTimestamp;
    }

    private static String getArtistId(String artist_Name) {
        return UUID.nameUUIDFromBytes(artist_Name.getBytes()).toString();
    }
}
