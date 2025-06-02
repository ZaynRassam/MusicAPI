package com.music.MusicDisplayer.album;

import com.music.MusicDisplayer.artist.Artist;
import com.music.MusicDisplayer.song.SongDto;
import com.music.MusicDisplayer.song.SongMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final AlbumMapper albumMapper;
    private final SongMapper songMapper;
    public AlbumController(AlbumService albumService, AlbumMapper albumMapper, SongMapper songMapper) {
        this.albumService = albumService;
        this.albumMapper = albumMapper;
        this.songMapper = songMapper;
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:5173")
    public List<AlbumDto> getAllAlbums() {
        return albumService.getAllAlbums().stream().map(albumMapper::toDto).toList();
    }

    @GetMapping("/{albumId}")
    @CrossOrigin(origins = "http://localhost:5173")
    public AlbumDto getAlbumByAlbumId(@PathVariable String albumId) {
        return albumMapper.toDto(albumService.getAlbumByAlbumId(albumId));
    }

    @GetMapping("/{albumId}/songs")
    @CrossOrigin(origins = "http://localhost:5173")
    public List<SongDto> getSongsByAlbumId(@PathVariable String albumId){
        return albumMapper.toDto(albumService.getAlbumByAlbumId(albumId)).getSongs();
    }

    @GetMapping("/{albumId}/details")
    public AlbumDetailDto getAlbumDetails(@PathVariable String albumId) throws Exception {
        Album album = albumService.getAlbumByAlbumId(albumId);
        Artist artist = albumService.getArtistByAlbumId(album);
        return albumMapper.toDetailDto(album, artist);
    }
}
