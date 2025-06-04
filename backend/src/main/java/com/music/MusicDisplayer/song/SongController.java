package com.music.MusicDisplayer.song;

import com.music.MusicDisplayer.album.AlbumDto;
import com.music.MusicDisplayer.album.AlbumMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;
    private final SongMapper songMapper;

    public SongController(SongService songService, SongMapper songMapper) {
        this.songService = songService;
        this.songMapper = songMapper;
    }

    @GetMapping("/all")
    public List<SongDto> getAllSongs() {
        return songService.getAllSongs().stream().map(songMapper::toDto).toList();
    }

    @GetMapping("/{songId}")
    public SongDto getSongById(@PathVariable String songId) {
        return songMapper.toDto(songService.getSongById(songId));
    }
}