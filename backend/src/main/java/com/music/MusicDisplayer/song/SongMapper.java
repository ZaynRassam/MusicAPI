package com.music.MusicDisplayer.song;
import com.music.MusicDisplayer.album.Album;
import org.springframework.stereotype.Component;

@Component
public class SongMapper {
    public SongDto toDto(Song song) {
        SongDto dto = new SongDto();
        dto.setSongId(song.getSongId());
        dto.setSongName(song.getSongName());
        dto.setDuration(song.getDuration());
        dto.setTrackNumber(song.getTrackNumber());
        return dto;
    }

    public Song toEntity(SongDto songDto) {
        Song song = new Song();
        song.setSongId(songDto.getSongId());
        song.setSongName(songDto.getSongName());
        song.setDuration(songDto.getDuration());
        song.setTrackNumber(songDto.getTrackNumber());
        return song;
    }
}
