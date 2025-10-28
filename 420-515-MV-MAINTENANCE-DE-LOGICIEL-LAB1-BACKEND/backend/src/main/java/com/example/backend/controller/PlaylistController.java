package com.example.backend.controller; import com.example.backend.entities.Playlist;
import com.example.backend.entities.Series;
import com.example.backend.repositories.PersonsRepository;
import com.example.backend.repositories.PlaylistRepository;
import com.example.backend.services.PlaylistService;
import org.springframework.web.bind.annotation.*;
import com.example.backend.utils.Sql;
import java.util.List;
@RestController @RequestMapping("api/playlist")
@CrossOrigin
public class PlaylistController {
    private final PlaylistService playlistService;
    public PlaylistController(PlaylistService playlistService) { this.playlistService = playlistService; }

    @GetMapping("/all")
    public List<Playlist> GetAll() {return playlistService.searchAllSeries(); }

    @GetMapping("/add")
    public String addPlaylist()throws Exception {
       return Sql.InsertSeriePlaylist();
    }

}