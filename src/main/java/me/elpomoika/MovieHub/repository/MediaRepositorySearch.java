package me.elpomoika.MovieHub.repository;

import me.elpomoika.MovieHub.domain.entity.Media;

import java.util.List;

public interface MediaRepositorySearch {
    List<Media> searchMedia(String title);
}
