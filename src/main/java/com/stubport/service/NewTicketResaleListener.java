package com.stubport.service;

import com.stubport.model.*;

public interface NewTicketResaleListener {
    void onTicketResale(User seller, User buyer, Ticket ticket);
}
