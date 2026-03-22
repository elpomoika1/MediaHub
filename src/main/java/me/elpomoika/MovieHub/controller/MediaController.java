package me.elpomoika.MovieHub.controller;

import lombok.RequiredArgsConstructor;
import me.elpomoika.MovieHub.dto.MediaPreviewDTO;
import me.elpomoika.MovieHub.dto.Genre;
import me.elpomoika.MovieHub.dto.MediaType;
import me.elpomoika.MovieHub.mapper.MediaMapper;
import me.elpomoika.MovieHub.service.MediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Validated
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;
    private final MediaMapper mediaMapper;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("genre") List<Genre> genres,
            @RequestParam("type") MediaType mediaType) throws IOException {
        mediaService.saveMovie(file, title, mediaType, genres);
        return ResponseEntity.ok("Uploaded");
    }

    @GetMapping("/{name}")
    public ResponseEntity<MediaPreviewDTO> getMedia(@PathVariable String slug) {
        return ResponseEntity.ok(
                mediaMapper.toDto(mediaService.getMediaBySlug(slug))
        );
    }

    @GetMapping("/medias")
    public ResponseEntity<List<MediaPreviewDTO>> getMedias() {
        return ResponseEntity.ok(mediaService.getMedias().stream()
                .map(mediaMapper::toDto)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/medias/{type}")
    public ResponseEntity<List<MediaPreviewDTO>> getMedias(@PathVariable MediaType type) {
        return ResponseEntity.ok(mediaService.getMedias().stream()
                .filter(m -> m.getType() == type)
                .map(mediaMapper::toDto)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/random")
    public ResponseEntity<MediaPreviewDTO> getRandomMedia() {
        return ResponseEntity.ok(
                mediaMapper.toDto(mediaService.getRandomMovie())
        );
    }

    @PostMapping("/rate")
    public ResponseEntity<String> rateMedia(@RequestParam String name,
                                            @RequestParam double rating) {
        mediaService.rateMedia(name, rating);
        return ResponseEntity.ok("rated");
    }
}
