package me.elpomoika.MovieHub.repository;

import me.elpomoika.MovieHub.domain.entity.Media;
import me.elpomoika.MovieHub.domain.enums.Genre;
import me.elpomoika.MovieHub.domain.enums.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long>, MediaRepositorySearch {
    Media findByName(String name);
    List<Media> findByGenresIn(List<Genre> genres);
    List<Media> findByType(MediaType mediaType);
    List<Media> findDistinctByTypeAndGenresIn(MediaType type, List<Genre> genres);
}
