package com.stubport.service;

import com.stubport.model.Event;


public interface NewEventListener {
    void onEventCreated(Event event);
}
