package com.example.backend.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.example.backend.entities.Playlist;
@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long>{


}
