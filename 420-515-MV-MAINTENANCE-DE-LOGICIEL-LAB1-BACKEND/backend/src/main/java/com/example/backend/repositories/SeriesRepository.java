package com.example.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entities.Series;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    List<Series> findByTitleContaining(String title);
    List<Series> findByGenre(String genre);
    List<Series> findByNbepisodes(int nbepisodes);
    List<Series> findByScore(int score);
    List<Series> findTop10ByOrderByViewsDesc();




}
