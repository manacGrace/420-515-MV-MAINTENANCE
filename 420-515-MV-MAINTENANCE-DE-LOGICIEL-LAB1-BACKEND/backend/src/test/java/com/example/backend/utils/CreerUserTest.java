package com.example.backend.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CreerUserTest {

    @Test
    void creerUser() throws Exception {
        boolean result = CreationUser.creerUser("chien@gmail.com", "123");
        Assertions.assertTrue(result);
    }

}
