package com.music.MusicDisplayer.artist;

import com.music.MusicDisplayer.album.AlbumDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;
    private final ArtistMapper artistMapper;
    public ArtistController(ArtistService artistService, ArtistMapper artistMapper) {
        this.artistService = artistService;
        this.artistMapper = artistMapper;
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:5173")
    public List<ArtistDto> getAllArtists() {
        return artistService.getAllArtists().stream().map(artistMapper::toDto).toList();
    }

    @GetMapping("/{artistId}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ArtistDto getArtistById(@PathVariable String artistId) {
        return artistMapper.toDto(artistService.getArtistById(artistId));
    }

    @GetMapping("/name/{artistName}")
    @CrossOrigin(origins = "http://localhost:5173")
    public Artist getArtistByArtistName(@PathVariable String artistName) {
        return artistService.getArtistByArtistName(artistName);
    }

    @GetMapping("/{artistId}/albums")
    @CrossOrigin(origins = "http://localhost:5173")
    public List<AlbumDto> getAllAlbumsByArtistId(@PathVariable String artistId){
        return artistMapper.toDto(artistService.getArtistById(artistId)).getAlbums();
    }
}
