package com.music.MusicDisplayer.artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private ArtistRepository artistRepository;
    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Artist getArtistById(String artistId) {
        return artistRepository.findById(artistId).orElse(null);
    }

    public Artist getArtistByArtistName(String artistName) {
        return artistRepository.getArtistByArtistName(artistName);
    }
}
