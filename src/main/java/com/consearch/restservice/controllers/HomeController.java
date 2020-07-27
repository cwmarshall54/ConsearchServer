package com.consearch.restservice.controllers;

import com.consearch.restservice.jedis.ElasticacheClient;
import com.consearch.restservice.models.Event;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class HomeController {
    ElasticacheClient elasticacheClient = new ElasticacheClient();

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/listTopArtists", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<String> get() throws Exception {
        return elasticacheClient.getTopArtists();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/home", produces = MediaType.APPLICATION_JSON_VALUE)
    public void getEventListByArtistName(@RequestParam(value = "artist_Name") String artist_Name) throws Exception {
        elasticacheClient.incrementOrCreateArtist(artist_Name);
    }
}
