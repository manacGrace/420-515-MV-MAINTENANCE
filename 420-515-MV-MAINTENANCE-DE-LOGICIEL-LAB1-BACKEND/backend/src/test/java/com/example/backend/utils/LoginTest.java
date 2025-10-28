package com.example.backend.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LoginTest {
    @Test
    void login() throws Exception {
        String result = CreationUser.login("chien@gmail.com", "123");
        Assertions.assertNotNull(result);
    }
}
