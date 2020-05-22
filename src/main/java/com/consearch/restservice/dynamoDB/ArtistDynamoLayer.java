package com.consearch.restservice.dynamoDB;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.xspec.S;
import com.amazonaws.services.transcribe.model.BadRequestException;
import com.consearch.restservice.models.Artist;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

public class ArtistDynamoLayer {
    private static final Regions REGION = Regions.US_EAST_1;

    private DynamoDBMapper dynamoDBMapper;

    public ArtistDynamoLayer() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(REGION).build();
        dynamoDBMapper = new DynamoDBMapper(client);
    }

    public Artist getArtist(String artist_Name) throws BadRequestException {
        try {
            return dynamoDBMapper.load(Artist.class, getArtistHash(artist_Name));
        } catch (Error err) {
            throw new BadRequestException("bad request");
        }
    }

    public Artist putArtist(String artist_Name) throws BadRequestException {
        Artist artistToSave = generatePutArtistObject(artist_Name);

        try {
            dynamoDBMapper.save(artistToSave);
            return artistToSave;
        } catch (Error err) {
            throw new BadRequestException("bad request");
        }
    }

    private Artist generatePutArtistObject(String artist_Name) {
        Artist putItem = new Artist();
        putItem.setArtist_Id(getArtistHash(artist_Name));
        putItem.setArtist_Name(artist_Name);

        return putItem;
    }

    private static String getArtistHash(String artist_Name) {
        return UUID.nameUUIDFromBytes(artist_Name.getBytes()).toString();
    }
}
