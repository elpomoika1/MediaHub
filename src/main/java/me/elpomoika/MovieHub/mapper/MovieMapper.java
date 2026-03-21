package me.elpomoika.MovieHub.mapper;

import me.elpomoika.MovieHub.entity.Movie;
import me.elpomoika.MovieHub.dto.MovieDTO;
import org.springframework.stereotype.Service;

@Service
public class MovieMapper {
    public MovieDTO toDto(Movie movie) {
        return MovieDTO.builder()
                .name(movie.getName())
                .title(movie.getTitle())
                .imageUrl(movie.getImageUrl())
                .rating(movie.getRating())
                .votes(movie.getVotes())
                .build();
    }
}
