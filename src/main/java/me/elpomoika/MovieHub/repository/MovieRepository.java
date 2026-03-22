package me.elpomoika.MovieHub.repository;

import me.elpomoika.MovieHub.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByName(String name);
}
