package com.example.backend.controller;

import com.example.backend.entities.Series;
import com.example.backend.services.SeriesService;
import com.example.backend.utils.Tendance;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/series")
@CrossOrigin
public class SeriesController {

    private final SeriesService seriesService;

    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @GetMapping("/all")
    public List<Series> getAll() {
        return seriesService.searchAll();
    }

    @GetMapping("/titre")
    public List<Series> getTitre(@RequestBody Map<String, Object> body) {
        String titre = body.get("titre").toString();
        return seriesService.searchByTitle(titre);
    }

    @GetMapping("/genre")
    public List<Series> getGenre(@RequestBody Map<String, Object> body) {
        String genre = body.get("genre").toString();
        return seriesService.searchByGenre(genre);
    }

    @GetMapping("/nbepisodes")
    public List<Series> getNbepisode(@RequestBody Map<String, Object> body) {
        int nbepisodes = Integer.parseInt(body.get("nbepisodes").toString());
        return seriesService.searchByNbEpisodes(nbepisodes);
    }

    @GetMapping("/score")
    public List<Series> getScore(@RequestBody Map<String, Object> body) {
        int score = Integer.parseInt(body.get("score").toString());
        return seriesService.searchByScore(score);
    }

    @GetMapping("/top10")
    public List<Series> getTop10MostViewed() {
        return seriesService.getTop10MostViewed();
    }

    @GetMapping("/trending")
    public List<Series> getpTop10Quality() {
        try {
            return seriesService.getTop10Quality();
        } catch (Exception e) {
            System.out.println("echec de fetch le series populaire: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @PostMapping("/rate")
    public Map<String, String> rate(@RequestBody Map<String, Object> body) {
        Map<String, String> response = new HashMap<>();
        try {
            String email = body.get("email").toString();
            long seriesId = Long.parseLong(body.get("seriesId").toString());
            int rating = Integer.parseInt(body.get("rating").toString());

            if (rating < 1 || rating > 5) {
                response.put("error", "rate doit etre entre 1 et 5");
                return response;
            }

            long userId = Tendance.getUserIdByEmail(email);
            Tendance.addVote(userId, seriesId, rating);

            response.put("message", "rate ajouter avec succes");
        } catch (Exception e) {
            response.put("error", e.getMessage());
        }
        return response;
    }
}
