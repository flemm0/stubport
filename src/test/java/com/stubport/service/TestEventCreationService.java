package com.stubport.service;

import com.stubport.model.Event;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestEventCreationService {

    public static Logger log = Logger.getLogger(TestEventCreationService.class);
    private final EventCreationService eventCreationService = new EventCreationService(
            new CopyOnWriteArrayList<Event>());

    @Test
    void testSingleEventCreation() {
        Event newEvent = eventCreationService.createEvent();
        log.info("Venue Name: " + newEvent.getVenueName());
        log.info("City: " + newEvent.getCity());
        log.info("ID: " + newEvent.getId());
        log.info("Event Date: " + newEvent.getEventDate());
        assertNotNull(newEvent);
    }

    @Test
    void testMultipleEventCreation() {
        List<Event> eventStore = new CopyOnWriteArrayList<>();
        EventCreationService localService = new EventCreationService(eventStore);

        for (int i = 0; i < 4; i++) {
            Event event = localService.createEvent();
            eventStore.add(event);
            log.info("Created Event: " + event.getId() + " at " + event.getVenueName() + ", " + event.getCity());
        }

        assertEquals(4, eventStore.size(), "Expected 4 events to be stored");

        for (Event e : eventStore) {
            assertNotNull(e.getId());
            assertNotNull(e.getVenueName());
            assertNotNull(e.getCity());
            assertNotNull(e.getEventDate());
        }
    }
}
