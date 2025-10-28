package com.example.backend.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.example.backend.entities.Playlist;
import com.example.backend.entities.Series;

public class Sql {

    public static String InsertSeriePlaylist() throws Exception {
        Connection con = null;
        Properties props = new Properties();
        PreparedStatement prtm = null;
        props = Connexion.getProps("application.properties");
        con = DriverManager.getConnection(
                props.getProperty("spring.datasource.url"),
                props.getProperty("spring.datasource.username"),
                props.getProperty("spring.datasource.password")
        );
        String sql = "select * from playlist ";
        prtm = con.prepareStatement(sql);
        ResultSet rs = prtm.executeQuery();
        ArrayList<Playlist> playlistTout = new ArrayList<>();
        ArrayList<Series> Serie = new ArrayList<>();
        String PlusSouvent = null;
        try {
            while (rs.next()) {
                Playlist playlist = new Playlist();
                playlist.setSerieId(rs.getLong(2));
                playlist.setUserId(rs.getLong(3));
                playlistTout.add(playlist);
            }

            for (Playlist playlist2 : playlistTout) {
                try {
                    String sql2 = "select * from series where id=?";
                    PreparedStatement prtm2 = con.prepareStatement(sql2);
                    prtm2.setLong(1, playlist2.getSerieId());

                    ResultSet rs2 = prtm2.executeQuery();
                    while (rs2.next()) {
                        Series series = new Series();
                        series.setId(rs2.getLong(1));
                        series.setTitle(rs2.getString(2));
                        series.setGenre(rs2.getString(3));
                        series.setNbepisodes(rs2.getInt(4));
                        series.setScore(rs2.getInt(5));

                        Serie.add(series);
                    }

                    rs2.close();
                    prtm2.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }


            for (Series series : Serie) {
                System.out.println(series.getTitle() + "   " + series.getGenre());
            }


            HashMap<String, Integer> genreCount = new HashMap<>();
            for (Series series : Serie) {
                String genre = series.getGenre();
                genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
            }


            int maxCount = 0;
            for (Map.Entry<String, Integer> entry : genreCount.entrySet()) {
                if (entry.getValue() > maxCount) {
                    PlusSouvent = entry.getKey().toLowerCase();
                    maxCount = entry.getValue();
                }
            }



        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            con.close();
            prtm.close();
        }
        return PlusSouvent;
    }


}
