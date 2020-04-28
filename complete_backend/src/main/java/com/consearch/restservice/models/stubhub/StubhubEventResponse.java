package com.consearch.restservice.models.stubhub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StubhubEventResponse {
    @JsonProperty("statusCode")
    int statusCode;
    EventResponseBody body;

    public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public EventResponseBody getBody() { return body; }
    public void setBody(EventResponseBody body) {
        this.body = body;
    }

    public List<StubhubEvent> getEvents() { return body.getStubhubEvents(); }

    public static class EventResponseBody {
        @JsonProperty("events")
        List<StubhubEvent> stubhubEvents;

        public List<StubhubEvent> getStubhubEvents() { return stubhubEvents; }
        public void setStubhubEvents(List<StubhubEvent> stubhubEvents) {
            this.stubhubEvents = stubhubEvents;
        }
    }
}
