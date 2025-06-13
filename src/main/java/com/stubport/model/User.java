package com.stubport.model;

import java.util.UUID;
import java.time.LocalDate;
import java.util.List;

public class User {
    private String name;
    private UUID id;
    private LocalDate dateOfBirth;
    private String email;
    private String gender;
    private List<Ticket> tickets;

    public User(
            String name, UUID id, LocalDate dateOfBirth,
            String email, String gender, List<Ticket> tickets) {
        this.name = name;
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.gender = gender;
        this.tickets = tickets;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public List<Ticket> getAllTickets() {
        return tickets;
    }

    public boolean addTicket(Ticket newTicket) {
        return this.tickets.add(newTicket);
    }

    public boolean removeTicket(Ticket ticket) {
        return this.tickets.remove(ticket);
    }

}
