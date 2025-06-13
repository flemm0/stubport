package com.stubport.service;

import com.stubport.model.User;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.log4j.Logger;

public class TestSignupService {

    public static Logger log = Logger.getLogger(TestSignupService.class);
    private final SignupService signupService = new SignupService(
            new CopyOnWriteArrayList<User>());

    @Test
    void testSingleUserCreation() {
        User newUser = signupService.createNewUser();
        assertNotNull(newUser);
        assertEquals(0, newUser.getAllTickets().size());
        log.info("Name: " + newUser.getName());
        log.info("ID: " + newUser.getId());
        log.info("Date of birth: " + newUser.getDateOfBirth());
        log.info("Email: " + newUser.getEmail());
        log.info("Gender: " + newUser.getGender());
    }

    @Test
    void testMultipleUserCreation() {
        List<User> userStore = new CopyOnWriteArrayList<>();
        SignupService localService = new SignupService(userStore);

        for (int i = 0; i < 10; i++) {
            User newUser = localService.createNewUser();
            userStore.add(newUser);
        }

        assertEquals(10, userStore.size(), "Expected 10 users to be created and stored");

        for (User u: userStore) {
            assertNotNull(u.getName());
            assertNotNull(u.getId());
            assertNotNull(u.getDateOfBirth());
            assertNotNull(u.getEmail());
            assertNotNull(u.getGender());
            assertNotNull(u.getAllTickets());
        }
    }
}
