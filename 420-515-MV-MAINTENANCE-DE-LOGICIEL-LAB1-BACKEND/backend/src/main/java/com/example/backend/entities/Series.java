package com.example.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Series {
    @Id
    private Long id;
    private String title;
    private String genre;
    private int nbepisodes;
    private int score;
    private long views;
    private long nbRate;
    private long totalvote;
}
