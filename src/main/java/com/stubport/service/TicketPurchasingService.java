package com.stubport.service;

import java.util.List;
import java.util.Random;
import com.stubport.model.Ticket;
import com.stubport.model.User;

public class TicketPurchasingService implements NewTicketListingListener {

    private final List<Ticket> availableTickets;
    private final List<User> userStore;
    private Random random = new Random();

    public TicketPurchasingService(
        List<Ticket> availableTickets,
        List<User> userStore
    ) {
        this.availableTickets = availableTickets;
        this.userStore = userStore;
    }

    @Override
    public void onNewTicketsListed(List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            sellTicket(ticket);
        }
    }

    public void sellTicket(Ticket ticket) {
        // grab random user from user store and transfer tickets
        int randInt = random.nextInt(userStore.size());
        User randomUser = userStore.get(randInt);
        randomUser.addTicket(ticket);
        availableTickets.remove(ticket);
    }
}
