package com.consearch.restservice.dynamoDB;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper.FailedBatch;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.transcribe.model.BadRequestException;
import com.consearch.restservice.models.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventDynamoLayer {
    private static final Regions REGION = Regions.US_EAST_1;

    private DynamoDBMapper dynamoDBMapper;

    public EventDynamoLayer() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(REGION).build();
        dynamoDBMapper = new DynamoDBMapper(client);
    }

    public PaginatedQueryList<Event> getEvents(String artist_Name) throws BadRequestException {
        Event event = generateEventObject(artist_Name, null);
        DynamoDBQueryExpression<Event> queryExpression = new DynamoDBQueryExpression<Event>()
                .withHashKeyValues(event);

        try {
            return dynamoDBMapper.query(Event.class, queryExpression);
        } catch (Error err) {
            throw new BadRequestException("bad request");
        }
    }

    public void putEvents(List<Event> events) throws BadRequestException {
        try {
            List<FailedBatch> failed = dynamoDBMapper.batchSave(events);
            System.out.println("Failed input number: " + failed.size());
        } catch (Error err) {
            throw new BadRequestException("bad request");
        }
    }

    private Event generateEventObject(String artist_Name, String event_Date) {
        Event putItem = new Event();
        putItem.setArtist_Id(getArtistId(artist_Name));

        if (event_Date != null) {
            putItem.setEvent_Date(event_Date);
        }

        return putItem;
    }

    private static String getArtistId(String artist_Name) {
        return UUID.nameUUIDFromBytes(artist_Name.getBytes()).toString();
    }
}
