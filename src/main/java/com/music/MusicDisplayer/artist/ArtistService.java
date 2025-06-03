package com.music.MusicDisplayer.artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public void add(Artist artist) {
        artistRepository.save(artist);
    }

    public ResponseEntity<Void> delete(String artistId){
        if (artistRepository.findById(artistId).isEmpty()) {
            System.out.println("Artist not found");
            return ResponseEntity.noContent().build();
        } else {
            System.out.println("Deleting artist: " + artistId);
            artistRepository.deleteById(artistId);
            return ResponseEntity.ok().build();
        }
    }
}
