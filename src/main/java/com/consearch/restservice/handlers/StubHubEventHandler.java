package com.consearch.restservice.handlers;

import com.consearch.restservice.apis.StubHubEventLambda;
import com.consearch.restservice.converters.EventConverter;
import com.consearch.restservice.dynamoDB.EventDynamoLayer;
import com.consearch.restservice.models.Event;
import com.consearch.restservice.models.stubhub.StubhubEvent;

import java.util.ArrayList;
import java.util.List;

public class StubHubEventHandler {
    private StubHubEventLambda stubHubEventLambda;
    private EventConverter eventConverter;

    public StubHubEventHandler() {
        stubHubEventLambda = new StubHubEventLambda();
        eventConverter = new EventConverter();
    }

    public List<Event> getEvents(String artist_Name) {
        List<StubhubEvent> stubHubEventList = stubHubEventLambda.getEvents(artist_Name);
        if (stubHubEventList.size() == 0) {
            return new ArrayList<>();
        }
        return eventConverter.convertStubhubEventListToEventList(stubHubEventList, artist_Name);
    }
}
