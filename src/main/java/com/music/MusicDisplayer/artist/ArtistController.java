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
    public List<ArtistDto> getAllArtists() {
        return artistService.getAllArtists().stream().map(artistMapper::toDto).toList();
    }

    @GetMapping("/{artistId}")
    public ArtistDto getArtistById(@PathVariable String artistId) {
        return artistMapper.toDto(artistService.getArtistById(artistId));
    }

    @GetMapping("/name/{artistName}")
    public Artist getArtistByArtistName(@PathVariable String artistName) {
        return artistService.getArtistByArtistName(artistName);
    }

    @GetMapping("/{artistId}/albums")
    public List<AlbumDto> getAllAlbumsByArtistId(@PathVariable String artistId){
        return artistMapper.toDto(artistService.getArtistById(artistId)).getAlbums();
    }

    @PostMapping("/add")
    public void addArtist(@RequestBody ArtistDto artistDto) {
        System.out.println("Adding artist: " + artistDto.getArtistName());
        artistService.add(artistMapper.toEntity(artistDto));
    }

    @DeleteMapping("/delete/{artistId}")
    public void deleteArtistById(@PathVariable String artistId){
        System.out.println("Deleting artist: " + artistId);
        artistService.delete(artistId);
    }

    @PutMapping("/update/{artistId}")
    public void updateArtist(@RequestBody ArtistDto artistDto, @PathVariable String artistId) {
        System.out.println("Updating artist: " + artistDto.getArtistName());
        artistService.update(artistDto, artistId);
    }
}
