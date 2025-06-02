package com.music.MusicDisplayer.album;

public class AlbumDto {
    public String albumId;
    public String albumName;
    public String albumUrl;
    public String releaseYear;
    public String description;

    public AlbumDto() {
    }

    public String getAlbumId() {
        return albumId;
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
