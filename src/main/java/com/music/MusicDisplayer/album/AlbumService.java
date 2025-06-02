package com.music.MusicDisplayer.album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AlbumService {

    private AlbumRepository albumRepository;
    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    public List<Album> getAllAlbumsByArtistId(@RequestBody String artistId){
        return albumRepository.getAlbumsByArtistId(artistId);
    }

    public Album getAlbumByAlbumId(@RequestBody String albumId) {
        return albumRepository.getAlbumByAlbumId(albumId);
    }
}
