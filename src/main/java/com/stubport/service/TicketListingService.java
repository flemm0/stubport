package com.stubport.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import com.stubport.model.Ticket;
import com.stubport.model.Event;
import com.github.javafaker.Faker;

public class TicketListingService implements NewEventListener {

    private final List<Ticket> availableTickets;
    private final List<NewTicketListingListener> listeners = new CopyOnWriteArrayList<>();
    private Faker faker = new Faker();

    public TicketListingService(List<Ticket> availableTickets) {
        this.availableTickets = availableTickets;
    }

    public void addTicketListingListener(NewTicketListingListener listener) {
        listeners.add(listener);
    }

    @Override
    public void onEventCreated(Event newEvent) {
        List<Ticket> newTickets = listNewEventTicketsForSale(newEvent);
        for (NewTicketListingListener listener : listeners) {
            listener.onNewTicketsListed(newTickets);
        }
    }

    public List<Ticket> listNewEventTicketsForSale(Event event) {
        List<Ticket> newTickets = new CopyOnWriteArrayList<Ticket>();
        int numTickets = faker.number().numberBetween(500, 2000);
        float ticketPrice = (float) faker.number().randomDouble(2, 50, 600);

        for (int i = 0; i < numTickets; i++) {
            Ticket ticket = new Ticket(ticketPrice, event);
            newTickets.add(ticket);
            availableTickets.addAll(newTickets);
        }

        return newTickets;
    }

}
