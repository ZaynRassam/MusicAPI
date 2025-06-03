package com.music.MusicDisplayer.album;

import com.music.MusicDisplayer.artist.Artist;
import com.music.MusicDisplayer.song.Song;
import com.music.MusicDisplayer.song.SongDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "rock_albums_1")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String albumId;
    @NotBlank(message = "Album name is mandatory")
    private String albumName;
    private String albumUrl;
    private String releaseYear;
    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "album_id")
    private List<Song> songs;

    public Album() {
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

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

}
