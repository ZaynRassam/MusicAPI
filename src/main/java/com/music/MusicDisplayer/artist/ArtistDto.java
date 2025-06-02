package com.music.MusicDisplayer.artist;

public class ArtistDto {
    public String artistId;
    public String artistName;
    public String artistUrl;
    public String bio;


    public ArtistDto() {
    }

    public ArtistDto(Artist artist) {
        this.artistId = artist.getArtistId();
        this.artistName = artist.getArtistName();
        this.artistUrl = artist.getArtistUrl();
        this.bio = artist.getBio();
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
}
