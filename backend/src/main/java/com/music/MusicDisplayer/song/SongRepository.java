package com.music.MusicDisplayer.song;

import com.music.MusicDisplayer.artist.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, String> {
}
