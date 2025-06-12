package com.stubport.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import com.stubport.model.Event;
import com.github.javafaker.Faker;

public class EventCreationService {

    private final List<Event> eventStore;
    private final List<NewEventListener> listeners = new CopyOnWriteArrayList<>();
    private Faker faker = new Faker();

    public EventCreationService(List<Event> eventStore) {
        this.eventStore = eventStore;
    }

    public void addEventListener(NewEventListener listener) {
        listeners.add(listener);
    }

    public void start() {
        while (true) {
            try {
                int randomInt = faker.number().numberBetween(0, 10);
                if (randomInt == 2) {
                    Event event = createEvent();
                    eventStore.add(event);
                    for (NewEventListener listener : listeners) {
                        listener.onEventCreated(event);
                    }
                } else {
                    Thread.sleep(30000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            Event event = createEvent();
            eventStore.add(event);
            // notify listeners a new event has been created
            for (NewEventListener listener : listeners) {
                listener.onEventCreated(event);
            }
        }
    }

    public Event createEvent() {
        UUID id = UUID.randomUUID();
        String venueName = generateVenueName();
        String city = faker.address().cityName();
        LocalDate eventDate = faker.date()
                .future(360, 60, TimeUnit.DAYS)
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return new Event(id, venueName, city, eventDate);
    }

    public String generateVenueName() {
        String venueName = faker.artist().name();
        venueName += " " + faker.space().constellation();
        return venueName;
    }
}
