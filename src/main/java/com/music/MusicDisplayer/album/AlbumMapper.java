package com.music.MusicDisplayer.album;

import org.springframework.stereotype.Component;

@Component
public class AlbumMapper {
    public static AlbumDto toDto(Album album) {
        AlbumDto dto = new AlbumDto();
        dto.setAlbumId(album.getAlbumId());
        dto.setAlbumName(album.getAlbumName());
        dto.setReleaseYear(album.getReleaseYear());
        dto.setDescription(album.getDescription());
        dto.setAlbumUrl(album.getAlbumUrl());
        return dto;
    }

    public static Album toEntity(AlbumDto albumDTO) {
        Album album = new Album();
        album.setAlbumId(albumDTO.getAlbumId());
        album.setAlbumName(albumDTO.getAlbumName());
        album.setReleaseYear(albumDTO.getReleaseYear());
        album.setDescription(albumDTO.getDescription());
        album.setAlbumUrl(albumDTO.getAlbumUrl());
        return album;
    }
}
