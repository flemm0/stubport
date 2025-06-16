package com.stubport.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import com.stubport.model.User;
import com.stubport.model.Ticket;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import com.github.javafaker.Faker;

public class SignupService {

    private final List<User> userStore;
    private Faker faker = new Faker();

    public SignupService(List<User> userStore) {
        this.userStore = userStore;
    }

    public void start() {
        while (true) {
            try {
                int randomInt = faker.number().numberBetween(0, 10);
                // automatically create 30 users upon creation
                if (randomInt <= 3 || userStore.size() < 400) {
                    User newUser = createNewUser();
                    userStore.add(newUser);
                } else {
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public User createNewUser() {
        String name = faker.name().fullName();
        UUID id = UUID.randomUUID();
        LocalDate dateOfBirth = faker.date()
                .birthday()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        String email = faker.internet().safeEmailAddress();
        String gender = faker.demographic().sex();
        return new User(name, id, dateOfBirth, email, gender, new CopyOnWriteArrayList<Ticket>());
    }
}
