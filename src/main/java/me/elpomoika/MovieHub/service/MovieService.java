package me.elpomoika.MovieHub.service;

import me.elpomoika.MovieHub.entity.Movie;
import me.elpomoika.MovieHub.repository.MovieRepository;
import me.elpomoika.MovieHub.util.SlugGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private S3FileStorageService s3StorageService;

    public void saveMovie(MultipartFile file, String title) throws IOException {
        Movie movie = Movie.builder()
                .title(title)
                .rating(0.0)
                .votes(0.0)
                .build();

        movie = movieRepository.save(movie);

        final String slug = SlugGenerator.generateSlug(title) + "-" + movie.getId();
        final String imageUrl = s3StorageService.uploadFile(file, slug);

        movie.setName(slug);
        movie.setImageUrl(imageUrl);

        movieRepository.save(movie);
    }

    public Movie getMovieBySlug(String slug) {
        return movieRepository.findByName(slug);
    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }
}