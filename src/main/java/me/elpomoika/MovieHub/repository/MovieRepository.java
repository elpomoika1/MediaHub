package me.elpomoika.MovieHub.repository;

import me.elpomoika.MovieHub.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "SELECT nextval('movie_seq')", nativeQuery = true)
    Long getNextId();
    Movie findByName(String name);
}
