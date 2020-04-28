package com.consearch.restservice.handlers;

import com.amazonaws.services.transcribe.model.BadRequestException;

import com.consearch.restservice.dynamoDB.ArtistDynamoLayer;
import com.consearch.restservice.models.Artist;

public class ArtistHandler {
	private ArtistDynamoLayer artistDynamoLayer;

	public ArtistHandler() {
		this.artistDynamoLayer = new ArtistDynamoLayer();
	}

	public Artist getArtist(String artist_Name) throws BadRequestException {
		Artist artist = artistDynamoLayer.getArtist(artist_Name);
        return artist;
	}

	public Artist putArtist(String artist_Name) throws BadRequestException {
		return artistDynamoLayer.putArtist(artist_Name);
	}
}