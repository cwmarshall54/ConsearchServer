package com.consearch.restservice.models.stubhub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StubhubEvent {
    @JsonProperty("name")
    String name;
    String type;
    String url;
    @JsonProperty("sales")
    EventStart dates;
    @JsonProperty("_embedded")
    Venues embeddedVenue;


    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() { return type; }
    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() { return url; }
    public void setUrl(String url) {
        this.url = url;
    }

    public EventStart getEventStart() { return dates; }
    public void setEventStart(EventStart dates) {
        this.dates = dates;
    }

    public String getDateTime() {
        return dates.getEventDate().getDateTime();
    }

    public String getEmbeddedVenue() {
        return embeddedVenue.getVenues().get(0).getName();
    }
    public void setEmbeddedVenue(Venues embeddedVenue) {
        this.embeddedVenue = embeddedVenue;
    }

    public String getCityName() {
        return embeddedVenue.getVenues().get(0).getCity().getName();
    }

    public String getCountryName() {
        return embeddedVenue.getVenues().get(0).getCountry().getName();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class EventStart {
        @JsonProperty("public")
        EventDate eventDate;
        public EventDate getEventDate() { return eventDate; }
        public void setEventDate(EventDate eventDate) {
            this.eventDate = eventDate;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        private static class EventDate {
            @JsonProperty("endDateTime")
            String dateTime;
            public String getDateTime() { return dateTime; }
            public void setDateTime(String dateTime) {
                this.dateTime = dateTime;
            }
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Venues {
        @JsonProperty("venues")
        List<Venue> venues;
        public List<Venue> getVenues() { return venues; }

        @JsonIgnoreProperties(ignoreUnknown = true)
        private static class Venue {
            String name;
            City city;
            Country country;
            public String getName() { return name; }
            public City getCity() { return city; }
            public Country getCountry() { return country; }

            @JsonIgnoreProperties(ignoreUnknown = true)
            private static class City {
                String name;
                public String getName() { return name; }
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            private static class Country {
                String name;
                public String getName() { return name; }
            }
        }
    }
}
