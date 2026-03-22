package me.elpomoika.MovieHub.controller;

import lombok.RequiredArgsConstructor;
import me.elpomoika.MovieHub.dto.MovieDTO;
import me.elpomoika.MovieHub.mapper.MovieMapper;
import me.elpomoika.MovieHub.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam String title) throws IOException {
        movieService.saveMovie(file, title);
        return ResponseEntity.ok("Uploaded");
    }

    @GetMapping("/{slug}")
    public ResponseEntity<MovieDTO> getMedia(@PathVariable String slug) {
        return ResponseEntity.ok(
                movieMapper.toDto(movieService.getMovieBySlug(slug))
        );
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieDTO>> getMovies() {
        return ResponseEntity.ok(movieService.getMovies().stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/random")
    public ResponseEntity<MovieDTO> getRandomMovie() {
        return ResponseEntity.ok(
                movieMapper.toDto(movieService.getRandomMovie())
        );
    }
}
