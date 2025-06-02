package com.music.MusicDisplayer.artist;

import com.music.MusicDisplayer.album.Album;
import com.music.MusicDisplayer.album.AlbumDto;
import com.music.MusicDisplayer.album.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtistMapper {

    private static AlbumMapper albumMapper;
    @Autowired
    public ArtistMapper(AlbumMapper albumMapper) {
        this.albumMapper = albumMapper;
    }

    public ArtistDto toDto(Artist artist) {
        ArtistDto dto = new ArtistDto();
        dto.setArtistId(artist.getArtistId());
        dto.setArtistName(artist.getArtistName());
        dto.setArtistUrl(artist.getArtistUrl());
        dto.setBio(artist.getBio());
        dto.setAlbums(artist.getAlbums().stream().map(albumMapper::toDto).toList());
        return dto;
    }

    public static Artist toEntity(ArtistDto artistDto) {
        Artist artist = new Artist();
        artist.setArtistId(artistDto.getArtistId());
        artist.setArtistName(artistDto.getArtistName());
        artist.setArtistUrl(artistDto.getArtistUrl());
        artist.setBio(artistDto.getBio());
        artist.setAlbums(artistDto.getAlbums().stream().map(albumMapper::toEntity).toList());
        System.out.println("Albums for artist: " + artist.getAlbums().size());
        return artist;
    }
}
