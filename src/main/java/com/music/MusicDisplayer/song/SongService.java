package com.music.MusicDisplayer.song;

import com.music.MusicDisplayer.artist.Artist;
import com.music.MusicDisplayer.artist.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private SongRepository songRepository;
    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Song getSongById(String songId) {
        return songRepository.findById(songId).orElse(null);
    }

    public List<Song> getSongsByArtistId(String artistId) {
        return songRepository.getSongsByArtistId(artistId);
    }

    public List<Song> getSongsByAlbumId(String albumId){
        return songRepository.getSongsByAlbumId(albumId);
    }
}
