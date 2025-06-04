package com.music.MusicDisplayer.artist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, String> {
    public Artist getArtistByArtistName(String artistName);
}
