package com.stubport.service;

import com.stubport.model.Event;
import com.stubport.model.Ticket;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestTicketListingService {

    public static Logger log = Logger.getLogger(TestTicketListingService.class);
    private final EventCreationService eventCreationService = new EventCreationService(
        new CopyOnWriteArrayList<>());
    private final TicketListingService ticketListingService = new TicketListingService(
            new CopyOnWriteArrayList<>());

    @Test
    void testTicketsListed() {
        Event newEvent = eventCreationService.createEvent();
        List<Ticket> newTickets = ticketListingService.listNewEventTicketsForSale(newEvent);
        assertNotNull(newTickets);
        log.info("Number of tickets created: " + newTickets.size());
        log.info("Ticket price: " + newTickets.get(0).getPrice());
    }

    @Test
    void testNumTicketsCreatedWithinRange() {
        Event newEvent = eventCreationService.createEvent();
        List<Ticket> newTickets = ticketListingService.listNewEventTicketsForSale(newEvent);
        assertTrue(newTickets.size() > 499);
        assertTrue(newTickets.size() < 2001);
    }
}
