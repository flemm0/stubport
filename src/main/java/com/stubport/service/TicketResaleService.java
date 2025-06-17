package com.stubport.service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import com.stubport.model.User;
import com.stubport.model.Ticket;

public class TicketResaleService {

    private final List<User> userStore;
    private Random random = new Random();

    public TicketResaleService(List<User> userStore) {
        this.userStore = userStore;
    }

    public void run() {
        // facilitate ticket transfer
        while (true) {
            try {
                facilitateTicketResaleTransaction();
                int millis = ThreadLocalRandom.current().nextInt(1_000, 10_001);
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public boolean facilitateTicketResaleTransaction() {
        User seller = userListTicketForResale();
        Ticket ticket = seller.getAllTickets().get(random.nextInt(seller.getAllTickets().size()));
        User buyer = userBuyResaleTicket(seller);
        boolean sellSuccess = seller.removeTicket(ticket);
        boolean buySuccess = buyer.addTicket(ticket);
        return sellSuccess && buySuccess;
    }

    public User userListTicketForResale() {
        List<User> usersWithTickets = userStore.stream()
                .filter(user -> !user.getAllTickets().isEmpty())
                .collect(Collectors.toList());
        return usersWithTickets.get(random.nextInt(usersWithTickets.size()));
    }

    public User userBuyResaleTicket(User seller) {
        List<User> buyers = userStore.stream()
                .filter(user -> !user.equals(seller))
                .collect(Collectors.toList());
        Collections.shuffle(buyers);
        int nBuyers = random.nextInt(15);
        Map<User, Double> offers = new HashMap<>();
        for (int i = 0; i < nBuyers; i++) {
            offers.put(buyers.get(i), random.nextDouble());
        }
        return offers.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
