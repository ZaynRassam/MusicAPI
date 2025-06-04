package com.music.MusicDisplayer.album;

import com.music.MusicDisplayer.artist.Artist;
import com.music.MusicDisplayer.song.Song;
import com.music.MusicDisplayer.song.SongDto;
import com.music.MusicDisplayer.song.SongMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlbumMapper {

    private final SongMapper songMapper;
    @Autowired
    public AlbumMapper(SongMapper songMapper) {
        this.songMapper = songMapper;
    }

    public AlbumDto toDto(Album album) {
        AlbumDto dto = new AlbumDto();
        dto.setAlbumId(album.getAlbumId());
        dto.setAlbumName(album.getAlbumName());
        dto.setReleaseYear(album.getReleaseYear());
        dto.setDescription(album.getDescription());
        dto.setAlbumUrl(album.getAlbumUrl());
        dto.setSongs(album.getSongs().stream().map(songMapper::toDto).toList());
        return dto;
    }

    // You need to pass in the Artist from the service layer
    public Album toEntity(AlbumDto albumDto) {
        Album album = new Album();
        album.setAlbumId(albumDto.getAlbumId());
        album.setAlbumName(albumDto.getAlbumName());
        album.setReleaseYear(albumDto.getReleaseYear());
        album.setDescription(albumDto.getDescription());
        album.setAlbumUrl(albumDto.getAlbumUrl());
        album.setSongs(albumDto.getSongs().stream().map(songMapper::toEntity).toList());
        return album;
    }

    public AlbumDetailDto toDetailDto(Album album, Artist artist) {
        AlbumDetailDto albumDetailDto = new AlbumDetailDto();
        albumDetailDto.setAlbumId(album.getAlbumId());
        albumDetailDto.setAlbumName(album.getAlbumName());
        albumDetailDto.setAlbumUrl(album.getAlbumUrl());
        albumDetailDto.setReleaseYear(album.getReleaseYear());
        albumDetailDto.setDescription(album.getDescription());
        albumDetailDto.setArtistName(artist.getArtistName());
        albumDetailDto.setSongs(album.getSongs().stream().map(songMapper::toDto).toList());
        return albumDetailDto;
    }

    public Album toEntity(AlbumDetailDto albumDetailDto) {
        Album album = new Album();
        album.setAlbumId(albumDetailDto.getAlbumId());
        album.setAlbumName(albumDetailDto.getAlbumName());
        album.setReleaseYear(albumDetailDto.getReleaseYear());
        album.setDescription(albumDetailDto.getDescription());
        album.setAlbumUrl(albumDetailDto.getAlbumUrl());
        album.setSongs(albumDetailDto.getSongs().stream().map(songMapper::toEntity).toList());
        return album;
    }
}
