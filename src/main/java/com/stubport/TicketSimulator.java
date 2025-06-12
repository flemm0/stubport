package com.stubport;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.stubport.model.Event;
import com.stubport.model.Ticket;
import com.stubport.model.User;
import com.stubport.service.*;


public class TicketSimulator {

    private final SignupService signupService;
    private final EventCreationService eventService;
    private final TicketListingService ticketListingService;
    private final TicketPurchasingService ticketPurchasingService;
    private final TicketResaleService ticketResaleService;
    private final List<User> userStore = new CopyOnWriteArrayList<>();
    private final List<Event> eventStore = new CopyOnWriteArrayList<>();
    private final List<Ticket> availableTickets = new CopyOnWriteArrayList<>();

    public TicketSimulator() {
        this.signupService = new SignupService(userStore);
        this.eventService = new EventCreationService(eventStore);
        this.ticketListingService = new TicketListingService(availableTickets);
        this.ticketPurchasingService = new TicketPurchasingService(availableTickets, userStore);
        this.ticketResaleService = new TicketResaleService(userStore);
        
        // listens for new events being created and lists tickets for sale
        eventService.addEventListener(ticketListingService);
        // listens for tickets being resold or listed
        ticketListingService.addTicketListingListener(ticketPurchasingService);
    }

    public void run() {
        signupService.start();
        eventService.start();
        // ticketSaleService.start(); // not needed if it doesn't do anything else
        ticketResaleService.run();
    }

    public static void main(String[] args) {
        new TicketSimulator().run();
    }
}
