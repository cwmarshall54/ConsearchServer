package com.consearch.restservice.controllers;

import com.consearch.restservice.handlers.EventHandler;
import com.consearch.restservice.models.Event;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class EventController {
    private EventHandler eventHandler = new EventHandler();

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/events", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getEventListByArtistName(
            @RequestParam(value = "artist_Name") String artist_Name,
            @RequestParam(value = "force_Refresh", required = false) boolean force_Refresh) throws Exception {
            return eventHandler.getEvents(artist_Name, force_Refresh);
    }
}
