package com.music.MusicDisplayer.artist;

import com.music.MusicDisplayer.album.Album;
import com.music.MusicDisplayer.album.AlbumDto;
import org.springframework.stereotype.Component;

@Component
public class ArtistMapper {
    public static ArtistDto toDto(Artist artist) {
        ArtistDto dto = new ArtistDto();
        dto.setArtistId(artist.getArtistId());
        dto.setArtistName(artist.getArtistName());
        dto.setArtistUrl(artist.getArtistUrl());
        dto.setBio(artist.getBio());
        return dto;
    }

    public static Artist toEntity(ArtistDto artistDto) {
        Artist artist = new Artist();
        artist.setArtistId(artistDto.getArtistId());
        artist.setArtistName(artistDto.getArtistName());
        artist.setArtistUrl(artistDto.getArtistUrl());
        artist.setBio(artistDto.getBio());
        return artist;
    }
}
