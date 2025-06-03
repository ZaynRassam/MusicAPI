package com.music.MusicDisplayer.artist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.music.MusicDisplayer.album.Album;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rock_artists_1")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String artistId;
    @NotBlank(message = "Artist name is mandatory")
    private String artistName;
    private String artistUrl;
    @Column(columnDefinition = "TEXT")
    private String bio;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id")
    private List<Album> albums;

    public Artist() {
    }

    public String getArtistId() {
        return artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistUrl() {
        return artistUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setArtistUrl(String artistUrl) {
        this.artistUrl = artistUrl;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
