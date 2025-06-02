package com.music.MusicDisplayer.artist;

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
        return artistService.getAllArtists().stream().map(artist -> {
            return artistMapper.toDto(artist);
        }).toList();
    }

    @GetMapping("/artist/{artistId}")
    @CrossOrigin(origins = "http://localhost:5173")
    public Artist getArtistById(@PathVariable String artistId) {
        return artistService.getArtistById(artistId);
    }

    @GetMapping("/name/{artistName}")
    @CrossOrigin(origins = "http://localhost:5173")
    public Artist getArtistByArtistName(@PathVariable String artistName) {
        return artistService.getArtistByArtistName(artistName);
    }
}
