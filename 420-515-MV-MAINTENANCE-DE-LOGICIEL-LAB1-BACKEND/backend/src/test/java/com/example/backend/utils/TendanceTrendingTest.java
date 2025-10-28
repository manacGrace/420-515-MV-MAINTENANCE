package com.example.backend.utils;

import com.example.backend.entities.Series;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TendanceTrendingTest {

    @Test
    void trending_shouldReturnListOfSeries() throws Exception {
        List<Series> result = Tendance.trending();
        Assertions.assertNotNull(result, "La liste doit pas être nulle");
        Assertions.assertTrue(result.size() <= 10, "La liste doit contenir au max 10 series");
    }
}
