package me.elpomoika.MovieHub.repository;

import me.elpomoika.MovieHub.domain.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
    Media findByName(String name);
}
