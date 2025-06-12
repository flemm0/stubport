package com.stubport.service;

import java.util.List;
import com.stubport.model.Ticket;


public interface NewTicketListingListener {
    void onNewTicketsListed(List<Ticket> tickets);
}
