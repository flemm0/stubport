package com.stubport.model;


import java.util.UUID;
import java.util.List;


public class User {
    private String name;
    private UUID id;
    private int age;
    private PhoneNumber phoneNumber;
    private Email email;
    private List<Ticket> tickets;

    public User(
        String name, UUID id, int age
        // PhoneNumber phoneNumber, Email email
        ) {
        this.name = name;
        this.id = id;
        this.age = age;
        // this.phoneNumber = phoneNumber;
        // this.email = email;
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

    public int getAge() {
        return age;
    }

    public void setAge(int newAge) {
        this.age = newAge;
    }

    public class PhoneNumber {
        private String value;

        public PhoneNumber(String value) {
            setValue(value);
        }

        public void setValue(String value) {
            if (!isValidPhoneNumber(value)) {
                throw new IllegalArgumentException("Invalid phone number: " + value);
            }
            if (value.matches("^\\d{10}$")) {
                this.value = value.substring(0, 3) + "-" +
                             value.substring(3, 6) + "-" +
                             value.substring(6, 10);
            } else {
                this.value = value;
            }
        }

        public boolean isValidPhoneNumber(String value) {
            return value != null && value.matches("^\\d{3}-?\\d{3}-?\\d{4}$");
        }

        public String getValue() {
            return value;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber.getValue();
    }

    public void setPhoneNumber(String newPhoneNumber) {
        this.phoneNumber.setValue(newPhoneNumber);
    }

    public class Email {
        private String value;

        public Email(String value) {
            setValue(value);
        }

        public void setValue(String value) {
            if (!isValidEmail(value)) {
                throw new IllegalArgumentException("Invalid email address: " + value);
            }
            this.value = value;
        }

        public boolean isValidEmail(String value) {
            return value != null && value.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public String getEmail() {
        return email.toString();
    }

    public void setEmail(String newValue) {
        this.email.setValue(newValue);
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