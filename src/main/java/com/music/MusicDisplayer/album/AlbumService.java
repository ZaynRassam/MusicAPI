package com.music.MusicDisplayer.album;

import com.music.MusicDisplayer.artist.Artist;
import com.music.MusicDisplayer.artist.ArtistRepository;
import com.music.MusicDisplayer.song.SongMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AlbumService {

    private AlbumRepository albumRepository;
    private ArtistRepository artistRepository;
    private SongMapper songMapper;
    @Autowired
    public AlbumService(AlbumRepository albumRepository, ArtistRepository artistRepository, SongMapper songMapper) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.songMapper = songMapper;
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    public Album getAlbumByAlbumId(@RequestBody String albumId) {
        return albumRepository.getAlbumByAlbumId(albumId);
    }

    public Artist getArtistByAlbumId(Album album) throws Exception {
        return artistRepository.findAll().stream()
                .filter(a -> a.getAlbums().contains(album))
                .findFirst()
                .orElseThrow(() -> new Exception("Artist not found"));
    }
}
