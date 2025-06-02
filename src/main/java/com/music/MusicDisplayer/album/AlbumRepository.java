package com.music.MusicDisplayer.album;

import com.music.MusicDisplayer.artist.Artist;
import com.music.MusicDisplayer.song.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, String> {
    public Album getAlbumByAlbumId(String albumId);

}
