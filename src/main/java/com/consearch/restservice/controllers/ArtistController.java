package com.consearch.restservice.controllers;

import com.consearch.restservice.models.Artist;
		import org.springframework.web.bind.annotation.GetMapping;
		import org.springframework.web.bind.annotation.PutMapping;
		import org.springframework.web.bind.annotation.RequestParam;
		import org.springframework.web.bind.annotation.RestController;

		import com.amazonaws.services.transcribe.model.BadRequestException;

		import com.consearch.restservice.handlers.ArtistHandler;

		import com.consearch.restservice.apis.StubHubEventLambda;

@RestController
public class ArtistController {
	private ArtistHandler artistHandler = new ArtistHandler();
	private StubHubEventLambda stubHubEventLambda = new StubHubEventLambda();

	@GetMapping("/artist")
	public Artist getArtistByName(@RequestParam(value = "artist_Name") String artist_Name) throws Exception {
		return artistHandler.getArtist(artist_Name);
	}

	@PutMapping("/artist")
	public Artist createArtistByName(@RequestParam(value = "artist_Name") String artist_Name) throws BadRequestException {
		return artistHandler.putArtist(artist_Name);
	}
}
