package com.music.MusicDisplayer.album;

import com.music.MusicDisplayer.artist.ArtistDto;
import com.music.MusicDisplayer.song.Song;
import com.music.MusicDisplayer.song.SongDto;

import java.util.List;

public class AlbumDto {
    private String albumId;
    private String albumName;
    private String albumUrl;
    private String releaseYear;
    private String description;

    private List<SongDto> songs;

    public AlbumDto() {
    }

    public String getAlbumId() {
        return this.albumId;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public String getAlbumUrl() {
        return this.albumUrl;
    }

    public String getReleaseYear() {
        return this.releaseYear;
    }
    public String getDescription() {
        return this.description;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setAlbumUrl(String albumUrl) {
        this.albumUrl = albumUrl;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SongDto> getSongs() {
        return this.songs;
    }

    public void setSongs(List<SongDto> songs) {
        this.songs = songs;
    }
}
