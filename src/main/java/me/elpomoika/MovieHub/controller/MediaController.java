package me.elpomoika.MovieHub.controller;

import lombok.Generated;
import lombok.RequiredArgsConstructor;
import me.elpomoika.MovieHub.domain.dto.MediaPreviewDTO;
import me.elpomoika.MovieHub.domain.dto.MediaRequest;
import me.elpomoika.MovieHub.domain.entity.Media;
import me.elpomoika.MovieHub.domain.enums.Genre;
import me.elpomoika.MovieHub.domain.enums.MediaType;
import me.elpomoika.MovieHub.mapper.MediaMapper;
import me.elpomoika.MovieHub.service.MediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;
    private final MediaMapper mediaMapper;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(
            @RequestPart("file") MultipartFile file,
            @RequestPart("data") MediaRequest request) throws IOException {
        mediaService.saveMovie(file, request);
        return ResponseEntity.ok("Uploaded");
    }

    @GetMapping("/{name}")
    public ResponseEntity<MediaPreviewDTO> getMedia(@PathVariable String name) {
        return ResponseEntity.ok(
                mediaMapper.toDto(mediaService.getMediaBySlug(name))
        );
    }

    @GetMapping("/list")
    public ResponseEntity<List<MediaPreviewDTO>> getMedias() {
        return ResponseEntity.ok(mediaService.getMedias().stream()
                .map(mediaMapper::toDto)
                .toList()
        );
    }

    @GetMapping("/list/{type}")
    public ResponseEntity<List<MediaPreviewDTO>> getMediasByType(
            @PathVariable MediaType type,
            @RequestParam(required = false) List<Genre> genres) {
        List<Media> medias;

        if (genres == null || genres.isEmpty()) {
            medias = mediaService.getMediasByType(type);
        } else {
            medias = mediaService.getMediasByTypeAndGenres(type, genres);
        }

        return ResponseEntity.ok(
                medias.stream()
                        .map(mediaMapper::toDto)
                        .toList());
    }

    @GetMapping("/random")
    public ResponseEntity<MediaPreviewDTO> getRandomMedia() {
        return ResponseEntity.ok(
                mediaMapper.toDto(mediaService.getRandomMovie())
        );
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<MediaPreviewDTO>> searchResults(@PathVariable String title) {
        String decodedTitle = URLDecoder.decode(title, StandardCharsets.UTF_8);
        return ResponseEntity.ok(mediaService.searchMedia(decodedTitle).stream()
                .map(mediaMapper::toDto)
                .collect(Collectors.toList()));
    }

    @PostMapping("/{name}/rate")
    public ResponseEntity<?> rateMedia(@PathVariable String name,
                                       @RequestParam Double rating) {
        mediaService.rateMedia(name, rating);
        return ResponseEntity.ok("rated");
    }
}