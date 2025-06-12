package com.stubport.model;


public class Ticket {
    private float price;
    private Event event;
    private boolean sold;

    public Ticket(float price, Event event) {
        this.price = price;
        this.event = event;
        this.sold = false;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float newPrice) {
        this.price = newPrice;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public boolean isSold() {
        return sold;
    }

    public void markSold() {
        this.sold = true;
    }
}
