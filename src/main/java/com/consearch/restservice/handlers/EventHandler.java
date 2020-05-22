package com.consearch.restservice.handlers;

import com.amazonaws.services.transcribe.model.BadRequestException;
import com.consearch.restservice.dynamoDB.EventDynamoLayer;
import com.consearch.restservice.models.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventHandler {
    private EventDynamoLayer eventDynamoLayer;
    private StubHubEventHandler stubHubEventHandler;

    public EventHandler() {
        eventDynamoLayer = new EventDynamoLayer();
        stubHubEventHandler = new StubHubEventHandler();
    }

    public List<Event> getEvents(String artist_Name, boolean force_Refresh) throws BadRequestException {
        if (force_Refresh) {
            return refreshEvents(artist_Name);
        } else {
            List<Event> events = eventDynamoLayer.getEvents(artist_Name);
            if (events.size() == 0) {
                return refreshEvents(artist_Name);
            }
            return events;
        }
    }

    public void putEvents(List<Event> events) throws BadRequestException {
        List<String> eventDates = new ArrayList<String>();
        List<Event> dedupEvents = new ArrayList<Event>();
        for (Event event : events) {
            String eventDate = event.getEvent_Date();
            if (!eventDates.contains(eventDate)) {
                eventDates.add(eventDate);
                dedupEvents.add(event);
            }
        }
        eventDynamoLayer.putEvents(dedupEvents);
    }

    public List<Event> refreshEvents(String artist_Name) {
        List<Event> newEvents = stubHubEventHandler.getEvents(artist_Name);
        putEvents(newEvents);
        return newEvents;
    }

    private boolean shouldAddToHash(HashMap<String, Event> hash, Event event) {
        return hash.containsKey(event.getEvent_Date());
    }
}
