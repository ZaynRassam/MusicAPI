package com.music.MusicDisplayer.artist;

import jakarta.persistence.*;

@Entity
@Table(name = "rock_artists")
public class Artist {
    @Id
    private String artistId;
    private String artistName;
    private String artistUrl;
    @Column(columnDefinition = "TEXT")
    private String bio;

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
}
