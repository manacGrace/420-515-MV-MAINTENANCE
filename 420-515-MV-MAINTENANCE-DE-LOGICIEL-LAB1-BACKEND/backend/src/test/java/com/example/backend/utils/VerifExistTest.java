package com.example.backend.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VerifExistTest {
    @Test
    void verifExiste() throws Exception {
        boolean result = CreationUser.verifExiste("chien@gmail.com");
        Assertions.assertFalse(result);
    }
}
