package com.stubport.service;

import java.util.List;
import com.stubport.model.User;
import java.util.UUID;
import com.github.javafaker.Faker;


public class SignupService {

    private final List<User> userStore;
    private Faker faker = new Faker();

    public SignupService(List<User> userStore) {
        this.userStore = userStore;
    }

    public void start() {
        while (true) {
            User user = createNewUser();
            userStore.add(user);
        }
    }

    public User createNewUser() {
        String firstName = faker.name().firstName();
        UUID id = UUID.randomUUID();
        int age = faker.number().numberBetween(18, 55);
        // String phoneNumber = faker.phoneNumber().toString();
        // String email = faker.internet().emailAddress();
        return new User(firstName, id, age);
    }
}
