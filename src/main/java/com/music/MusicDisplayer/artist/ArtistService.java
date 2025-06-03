package com.music.MusicDisplayer.artist;

import com.music.MusicDisplayer.album.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistService {

    private ArtistRepository artistRepository;
    private AlbumMapper albumMapper;
    @Autowired
    public ArtistService(ArtistRepository artistRepository, AlbumMapper albumMapper) {
        this.artistRepository = artistRepository;
        this.albumMapper = albumMapper;
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

    public ResponseEntity<Void> update(ArtistDto artistDto, String artistId) {
        Artist artistToUpdate = artistRepository.findById(artistId).orElse(null);
        if (artistToUpdate == null) {
            System.out.println("Artist not found");
            return ResponseEntity.noContent().build();
        } else {
            artistToUpdate.setArtistId(artistId);
            artistToUpdate.setArtistName(artistDto.getArtistName());
            artistToUpdate.setArtistUrl(artistDto.getArtistUrl());
            artistToUpdate.setBio(artistDto.getBio());
            artistToUpdate.setAlbums(artistDto.getAlbums().stream().map(albumMapper::toEntity).collect(Collectors.toCollection(ArrayList::new)));
            try {
                System.out.println("Updating artist: " + artistToUpdate.getArtistName() + ", id: " + artistToUpdate.getArtistId());
                artistRepository.save(artistToUpdate);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return ResponseEntity.ok().build();
        }
    }
}
