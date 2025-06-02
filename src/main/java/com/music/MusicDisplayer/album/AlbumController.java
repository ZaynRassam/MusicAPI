package com.music.MusicDisplayer.album;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final AlbumMapper albumMapper;
    public AlbumController(AlbumService albumService, AlbumMapper albumMapper) {
        this.albumService = albumService;
        this.albumMapper = albumMapper;
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:5173")
    public List<AlbumDto> getAllAlbums() {
        return albumService.getAllAlbums().stream().map(album -> {
            return AlbumMapper.toDto(album);
        }).toList();
    }

    @GetMapping("/artist/{artistId}")
    @CrossOrigin(origins = "http://localhost:5173")
    public List<AlbumDto> getAllAlbumsByArtistId(@PathVariable String artistId) {
        return albumService.getAllAlbumsByArtistId(artistId).stream().map(album -> {
            return AlbumMapper.toDto(album);
        }).toList();
    }

    @GetMapping("/{albumId}")
    @CrossOrigin(origins = "http://localhost:5173")
    public AlbumDto getAlbumByAlbumId(@PathVariable String albumId) {
        return AlbumMapper.toDto(albumService.getAlbumByAlbumId(albumId));
    }
}
