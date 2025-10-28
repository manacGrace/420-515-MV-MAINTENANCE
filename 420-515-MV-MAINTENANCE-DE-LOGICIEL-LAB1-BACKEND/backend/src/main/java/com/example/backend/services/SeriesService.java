package com.example.backend.services;

import java.util.ArrayList;
import java.util.List;

import com.example.backend.entities.Series;
import com.example.backend.repositories.SeriesRepository;
import org.springframework.stereotype.Service;
import com.example.backend.utils.Tendance;

@Service
public class SeriesService {
    private final SeriesRepository seriesRepository;

    public SeriesService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public List<Series> searchAll() {
        return seriesRepository.findAll();
    }

    public List<Series> searchByTitle(String title) {
        return seriesRepository.findByTitleContaining(title);
    }

    public List<Series> searchByGenre(String genre) {
        return seriesRepository.findByGenre(genre);
    }

    public List<Series> searchByNbEpisodes(int nbEpisodes) {
        return seriesRepository.findByNbepisodes(nbEpisodes);
    }

    public List<Series> searchByScore(int score){return  seriesRepository.findByScore(score);}

    public List<Series> searchByCustomQuery(String titre, String genre, int nbEpisode) {
        List<Series> series = new ArrayList<>();
        return series;
    }
    public List<Series> getTop10MostViewed() {
        return seriesRepository.findTop10ByOrderByViewsDesc();
    }
    public List<Series> getTop10Quality() throws Exception{
        return Tendance.trending();
    }


}
