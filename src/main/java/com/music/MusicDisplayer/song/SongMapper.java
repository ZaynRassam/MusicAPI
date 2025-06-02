package com.music.MusicDisplayer.song;
import org.springframework.stereotype.Component;

@Component
public class SongMapper {
    public static SongDto toDto(Song song) {
        SongDto dto = new SongDto();
        dto.setSongId(song.getSongId());
        dto.setSongName(song.getSongName());
        dto.setAlbumId(song.getAlbumId());
        dto.setArtistId(song.getArtistId());
        dto.setDuration(song.getDuration());
        dto.setTrackNumber(song.getTrackNumber());
        return dto;
    }

    public static Song toEntity(SongDto songDto) {
        Song song = new Song();
        song.setSongId(songDto.getSongId());
        song.setSongName(songDto.getSongName());
        song.setAlbumId(songDto.getAlbumId());
        song.setArtistId(songDto.getArtistId());
        song.setDuration(songDto.getDuration());
        song.setTrackNumber(songDto.getTrackNumber());
        return song;
    }
}
