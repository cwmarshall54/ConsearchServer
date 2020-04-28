package com.consearch.restservice.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Review")
public class Review {
    private String event_Id;
    private String time_stamp;
    private String review_text;

    @DynamoDBHashKey(attributeName="eventId")
    public String getEvent_Id() { return event_Id; }
    public void setEvent_Id(String event_Id) { this.event_Id = event_Id; }

    @DynamoDBRangeKey(attributeName="timestamp")
    public String getTime_stamp() { return time_stamp; }
    public void setTime_stamp(String time_stamp) {this.time_stamp = time_stamp; }

    @DynamoDBAttribute(attributeName="reviewText")
    public String getReview_text() { return review_text; }
    public void setReview_text(String review_text) {this.review_text = review_text; }
}
