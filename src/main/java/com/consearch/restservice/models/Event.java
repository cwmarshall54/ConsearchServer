package com.consearch.restservice.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Events")
public class Event {
    private String artist_Id;
    private String event_Date;
    private String artist_Name;
    private String event_Name;
    private String stubHub_url;
    private String venue;
    private String cityName;
    private String countryName;

    @DynamoDBHashKey(attributeName="Artist_Id")
    public String getArtist_Id() { return artist_Id; }
    public void setArtist_Id(String artist_Id) { this.artist_Id = artist_Id; }

    @DynamoDBRangeKey(attributeName="Event_Date")
    public String getEvent_Date() { return event_Date; }
    public void setEvent_Date(String event_Date) {this.event_Date = event_Date; }

    @DynamoDBAttribute(attributeName="Artist_Name")
    public String getArtist_Name() { return artist_Name; }
    public void setArtist_Name(String artist_Name) {this.artist_Name = artist_Name; }

    @DynamoDBAttribute(attributeName="Event_Name")
    public String getEvent_Name() { return event_Name; }
    public void setEvent_Name(String event_Name) {this.event_Name = event_Name; }

    @DynamoDBAttribute(attributeName="StubHub_Url")
    public String getStubHub_url() { return stubHub_url; }
    public void setStubHub_url(String stubHub_url) {this.stubHub_url = stubHub_url; }

    @DynamoDBAttribute(attributeName="Event_Venue")
    public String getVenue() { return venue; }
    public void setVenue(String venue) {this.venue = venue; }

    @DynamoDBAttribute(attributeName="Event_City")
    public String getCityName() { return cityName; }
    public void setCityName(String cityName) {this.cityName = cityName; }

    @DynamoDBAttribute(attributeName="Event_Country")
    public String getCountryName() { return countryName; }
    public void setCountryName(String countryName) {this.countryName = countryName; }
}
