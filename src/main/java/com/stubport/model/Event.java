package com.stubport.model;

import java.time.LocalDate;
import java.util.UUID;

public class Event {
    private UUID id;
    private String venueName;
    private String city;
    private LocalDate eventDate;

    public Event() {
    }

    public Event(UUID id, String venueName,
            String city, LocalDate eventDate) {
        this.id = id;
        this.eventDate = eventDate;
        this.city = city;
        this.venueName = venueName;
    }

    public UUID getId() {
        return id;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getCity() {
        return city;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

}
