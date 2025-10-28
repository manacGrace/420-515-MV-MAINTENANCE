package com.example.backend.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TendanceAddVoteTest {

    @Test
    void addVote_shouldNotThrowException() {
        Assertions.assertDoesNotThrow(() -> {
            long userId = Tendance.getUserIdByEmail("chien@gmail.com");
            Tendance.addVote(userId, 1, 3);
        });
    }
}
