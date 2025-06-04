package com.music.MusicDisplayer.song;

import com.music.MusicDisplayer.album.Album;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "rock_songs_1")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String songId;
    @NotBlank(message = "Song name is mandatory")
    private String songName;
    private Integer duration;
    private Integer trackNumber;

    public Song() {
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }
}


