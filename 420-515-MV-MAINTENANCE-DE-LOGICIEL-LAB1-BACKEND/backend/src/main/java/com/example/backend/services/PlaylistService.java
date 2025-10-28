package com.example.backend.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.backend.entities.Playlist;
import com.example.backend.repositories.PlaylistRepository;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public List<Playlist> searchAllSeries() {
        return playlistRepository.findAll();
    }


}
