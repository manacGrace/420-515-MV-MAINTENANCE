package com.example.backend.utils;

import com.example.backend.entities.Series;
import java.sql.*;
import java.util.*;

public class Tendance {


    public static List<Series> trending() throws Exception {
        List<Series> seriesTout = new ArrayList<>();
        List<Series> series10 = new ArrayList<>();
        Properties props = Connexion.getProps("application.properties");

        try (Connection con = DriverManager.getConnection(
                props.getProperty("spring.datasource.url"),
                props.getProperty("spring.datasource.username"),
                props.getProperty("spring.datasource.password"));
             PreparedStatement prtm = con.prepareStatement("SELECT * FROM series");
             ResultSet rs = prtm.executeQuery()) {

            while (rs.next()) {
                Series series = new Series();
                series.setId(rs.getLong("id"));
                series.setTitle(rs.getString("title"));
                series.setGenre(rs.getString("genre"));
                series.setNbepisodes(rs.getInt("nbepisodes"));
                series.setScore(rs.getInt("score"));
                series.setViews(rs.getLong("views"));
                series.setNbRate(rs.getLong("nbRate"));
                series.setTotalvote(rs.getLong("totalVote"));
                seriesTout.add(series);
            }
        } catch (Exception e) {
            System.out.println("Erreur en fetchant les series: " + e.getMessage());
        }


        for (int i = 0; i < 10 && !seriesTout.isEmpty(); i++) {
            Series pretendant = seriesTout.get(0);
            long scorePretendant = (pretendant.getViews() * pretendant.getNbRate())
                    + (pretendant.getScore() * pretendant.getNbepisodes());

            for (Series s : seriesTout) {
                long challenger = (s.getViews() * s.getNbRate())
                        + (s.getScore() * s.getNbepisodes());
                if (challenger > scorePretendant) {
                    pretendant = s;
                    scorePretendant = challenger;
                }
            }

            series10.add(pretendant);
            seriesTout.remove(pretendant);
        }

        return series10;
    }


    public static long getUserIdByEmail(String email) throws Exception {
        Properties props = Connexion.getProps("application.properties");

        try (Connection con = DriverManager.getConnection(
                props.getProperty("spring.datasource.url"),
                props.getProperty("spring.datasource.username"),
                props.getProperty("spring.datasource.password"));
             PreparedStatement prtm = con.prepareStatement("SELECT id FROM users WHERE email = ?")) {

            prtm.setString(1, email);
            try (ResultSet rs = prtm.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong("id");
                } else {
                    throw new RuntimeException("User pas trouver avec son email: " + email);
                }
            }
        }
    }


    public static void addVote(long userId, long serieId, int voteScore) throws Exception {
        Properties props = Connexion.getProps("application.properties");

        try (Connection con = DriverManager.getConnection(
                props.getProperty("spring.datasource.url"),
                props.getProperty("spring.datasource.username"),
                props.getProperty("spring.datasource.password"))) {


            Integer ancienVote = null;
            try (PreparedStatement prtm = con.prepareStatement(
                    "SELECT score FROM vote WHERE userId = ? AND serieId = ?")) {
                prtm.setLong(1, userId);
                prtm.setLong(2, serieId);
                try (ResultSet rsVote = prtm.executeQuery()) {
                    if (rsVote.next()) {
                        ancienVote = rsVote.getInt("score");
                    }
                }
            }


            if (ancienVote != null) {
                try (PreparedStatement prtmUpdate = con.prepareStatement(
                        "UPDATE vote SET score = ? WHERE userId = ? AND serieId = ?")) {
                    prtmUpdate.setInt(1, voteScore);
                    prtmUpdate.setLong(2, userId);
                    prtmUpdate.setLong(3, serieId);
                    prtmUpdate.executeUpdate();
                }
            } else {
                try (PreparedStatement prtmInsert = con.prepareStatement(
                        "INSERT INTO vote (userId, serieId, score) VALUES (?, ?, ?)")) {
                    prtmInsert.setLong(1, userId);
                    prtmInsert.setLong(2, serieId);
                    prtmInsert.setInt(3, voteScore);
                    prtmInsert.executeUpdate();
                }
            }


            long totalVote = 0;
            long nbRate = 0;
            try (PreparedStatement prtmSelect = con.prepareStatement(
                    "SELECT totalVote, nbRate FROM series WHERE id = ?")) {
                prtmSelect.setLong(1, serieId);
                try (ResultSet rs = prtmSelect.executeQuery()) {
                    if (rs.next()) {
                        totalVote = rs.getLong("totalVote");
                        nbRate = rs.getLong("nbRate");
                    } else {
                        return;
                    }
                }
            }

            long nouveauTotalVote = totalVote;
            long nouveauNbRate = nbRate;

            if (ancienVote != null) {
                nouveauNbRate = nbRate - ancienVote + voteScore;
            } else {
                nouveauTotalVote = totalVote + 1;
                nouveauNbRate = nbRate + voteScore;
            }

            double nouveauScore = (double) nouveauTotalVote / nouveauNbRate;

            int roundedScore = (int) Math.round(nouveauScore);


            try (PreparedStatement prtmUpdateSeries = con.prepareStatement(
                    "UPDATE series SET nbRate = ?, totalVote = ?, score = ? WHERE id = ?")) {
                prtmUpdateSeries.setLong(1, nouveauNbRate);
                prtmUpdateSeries.setLong(2, nouveauTotalVote);
                prtmUpdateSeries.setInt(3, roundedScore);
                prtmUpdateSeries.setLong(4, serieId);
                prtmUpdateSeries.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println("erreur dans le update du rating: " + e.getMessage());
        }
    }


}
