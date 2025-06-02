package com.music.MusicDisplayer.album;

import jakarta.persistence.*;

@Entity
@Table(name = "rock_albums")
public class Album {
    @Id
    private String albumId;
    @JoinColumn(name = "artistId")
    private String artistId;
    private String albumName;
    private String albumUrl;
    private String releaseYear;
    @Column(columnDefinition = "TEXT")
    private String description;

    public Album() {
    }

    public String getAlbumId() {
        return albumId;
    }
    public String getArtistId() {
        return artistId;
    }
    public String getAlbumName() {
        return albumName;
    }
    public String getAlbumUrl() {
        return albumUrl;
    }
    public String getReleaseYear() {
        return releaseYear;
    }
    public String getDescription() {
        return description;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
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
}
