package com.stubport.service;

import java.util.List;
import java.util.Random;
import com.stubport.model.User;


public class TicketResaleService {
    
    private final List<User> userStore;
    private Random random = new Random();

    public TicketResaleService(List<User> userStore) {
        this.userStore = userStore;
    }

    public void run() {
        // facilitate ticket transfer
    }

    public boolean facilitateTicketResaleTransaction() {
        int sellerIdx = random.nextInt(userStore.size());
        int buyerIdx = random.nextInt(userStore.size());
        User seller = userStore.get(sellerIdx);
        User buyer = userStore.get(buyerIdx);
        boolean sellSuccess = seller.removeTicket(null);
        boolean buySuccess = buyer.addTicket(null);
        return sellSuccess && buySuccess;
    }
}
