package com.music.MusicDisplayer.artist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.MusicDisplayer.album.AlbumDto;
import com.music.MusicDisplayer.openai.OpenAi;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;
    private final ArtistMapper artistMapper;
    private final OpenAi openai;
    private final ChatClient chatClient;
    public ArtistController(ArtistService artistService, ArtistMapper artistMapper, OpenAi openai, ChatClient.Builder chatClientBuilder) {
        this.artistService = artistService;
        this.artistMapper = artistMapper;
        this.openai = openai;
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/all")
    public List<ArtistDto> getAllArtists() {
        return artistService.getAllArtists().stream().map(artistMapper::toDto).toList();
    }

    @GetMapping("/{artistId}")
    public ArtistDto getArtistById(@PathVariable String artistId) {
        return artistMapper.toDto(artistService.getArtistById(artistId));
    }

    @GetMapping("/name/{artistName}")
    public Artist getArtistByArtistName(@PathVariable String artistName) {
        return artistService.getArtistByArtistName(artistName);
    }

    @GetMapping("/{artistId}/albums")
    public List<AlbumDto> getAllAlbumsByArtistId(@PathVariable String artistId){
        return artistMapper.toDto(artistService.getArtistById(artistId)).getAlbums();
    }

    @PostMapping("/add")
    public void addArtist(@RequestBody ArtistDto artistDto) {
        System.out.println("Adding artist: " + artistDto.getArtistName());
        artistService.add(artistMapper.toEntity(artistDto));
    }

    @DeleteMapping("/delete/{artistId}")
    public void deleteArtistById(@PathVariable String artistId){
        System.out.println("Deleting artist: " + artistId);
        artistService.delete(artistId);
    }

    @PutMapping("/update/{artistId}")
    public void updateArtist(@RequestBody ArtistDto artistDto, @PathVariable String artistId) {
        System.out.println("Updating artist: " + artistDto.getArtistName());
        artistService.update(artistDto, artistId);
    }

    @GetMapping("/ai-complete/{artistId}")
    public void getAiArtistUpdate(@PathVariable String artistId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ArtistDto artistDto = artistMapper.toDto(artistService.getArtistById(artistId));
        String json = mapper.writeValueAsString(artistDto);
        String response = openai.createArtistUsingAi(json, chatClient);
//        System.out.println(response);

        ArtistDto updatedArtistDto = mapper.readValue(response, ArtistDto.class);
        artistService.update(updatedArtistDto, artistId);
    }
}
