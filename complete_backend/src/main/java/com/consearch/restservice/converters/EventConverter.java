package com.consearch.restservice.converters;

import com.consearch.restservice.models.Event;
import com.consearch.restservice.models.stubhub.StubhubEvent;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class EventConverter {
    public List<Event> convertStubhubEventListToEventList(List<StubhubEvent> stubhubEvents, String artist_Name) {
        return stubhubEvents.stream()
                .map(event -> convertStubhubEventToEvent(event, artist_Name))
                .collect(Collectors.toList());
    }

    public Event convertStubhubEventToEvent(StubhubEvent stubhubEvent, String artist_Name) {
        Event event = new Event();
        event.setArtist_Id(getArtistId(artist_Name));
        event.setEvent_Date(stubhubEvent.getDateTime());
        event.setArtist_Name(artist_Name);
        event.setEvent_Name(stubhubEvent.getName());
        event.setStubHub_url(stubhubEvent.getUrl());
        event.setVenue(stubhubEvent.getEmbeddedVenue());
        event.setCityName(stubhubEvent.getCityName());
        event.setCountryName(stubhubEvent.getCountryName());
        return event;
    }

    private static String getArtistId(String artist_Name) {
        return UUID.nameUUIDFromBytes(artist_Name.getBytes()).toString();
    }
}
