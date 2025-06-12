package com.stubport.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import com.stubport.model.Ticket;
import com.stubport.model.Event;


public class TicketListingService implements NewEventListener {

    private final List<Ticket> availableTickets;
    private final List<NewTicketListingListener> listeners = new CopyOnWriteArrayList<>();

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
        for (int i = 0; i < 10; i++) {
            Ticket ticket = new Ticket(i, null);
            newTickets.add(ticket);
            availableTickets.addAll(newTickets);
        }
        System.out.println("Created 10 tickets for: " + event.getId());
        return newTickets;
    }

    public void start() {

    }

}