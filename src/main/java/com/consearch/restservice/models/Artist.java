package com.consearch.restservice.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Artists")
public class Artist {
    private String artist_Id;
    private String artist_Name;

    @DynamoDBHashKey(attributeName="Artist_Id")
    public String getArtist_Id() { return artist_Id; }
    public void setArtist_Id(String artist_Id) { this.artist_Id = artist_Id; }

    @DynamoDBAttribute(attributeName="Artist_Name")
    public String getArtist_Name() { return artist_Name; }
    public void setArtist_Name(String artist_Name) {this.artist_Name = artist_Name; }
}
