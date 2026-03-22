package me.elpomoika.MovieHub.mapper;

import me.elpomoika.MovieHub.entity.Media;
import me.elpomoika.MovieHub.dto.MediaPreviewDTO;
import me.elpomoika.MovieHub.entity.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaMapper {
    public MediaPreviewDTO toDto(Media media) {
        return MediaPreviewDTO.builder()
                .name(media.getName())
                .title(media.getTitle())
                .imageUrl(media.getImageUrl())
                .rating(calculateAverageRating(media.getRating()))
                .votes(calculateAverageRating(media.getRating()))
                .build();
    }

    private double calculateAverageRating(List<Rating> ratings) {
        return ratings.stream()
                .mapToDouble(Rating::getValue)
                .average()
                .orElse(0.0);
    }
}