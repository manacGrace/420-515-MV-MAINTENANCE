package com.example.backend.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TendanceGetUserIdByEmailTest {

    @Test
    void getUserIdByEmail_shouldReturnUserId() throws Exception {
        long userId = Tendance.getUserIdByEmail("chien@gmail.com");
        Assertions.assertTrue(userId > 0, "id utilisateur doit etre superieur a 0");
    }

    @Test
    void getUserIdByEmail_shouldThrowExceptionForInvalidEmail() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            Tendance.getUserIdByEmail("fakeemail@inexistant.com");
        });
    }
}
