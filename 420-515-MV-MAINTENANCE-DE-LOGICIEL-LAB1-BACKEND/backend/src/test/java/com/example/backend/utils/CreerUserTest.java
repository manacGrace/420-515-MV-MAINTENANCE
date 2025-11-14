package com.example.backend.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CreerUserTest {

    @Test
    void creerUser() throws Exception {
        // Use a unique email to avoid conflicts with existing users
        String uniqueEmail = "test" + System.currentTimeMillis() + "@gmail.com";
        boolean result = CreationUser.creerUser(uniqueEmail, "123");
        Assertions.assertTrue(result);
    }

}
