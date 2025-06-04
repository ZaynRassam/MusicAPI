package com.music.MusicDisplayer.artist;

import com.music.MusicDisplayer.album.Album;
import com.music.MusicDisplayer.album.AlbumDto;

import java.util.List;

public class ArtistDto {
    public String artistId;
    public String artistName;
    public String artistUrl;
    public String bio;
    public List<AlbumDto> albums;

    public ArtistDto() {
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistUrl() {
        return artistUrl;
    }

    public void setArtistUrl(String artistUrl) {
        this.artistUrl = artistUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<AlbumDto> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumDto> albums) {
        this.albums = albums;
    }
}
