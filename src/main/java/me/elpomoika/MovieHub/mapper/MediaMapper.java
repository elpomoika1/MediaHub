package me.elpomoika.MovieHub.mapper;

import me.elpomoika.MovieHub.domain.entity.Media;
import me.elpomoika.MovieHub.domain.dto.MediaPreviewDTO;
import me.elpomoika.MovieHub.domain.entity.Rating;
import me.elpomoika.MovieHub.domain.entity.Votes;
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
                .votes(calculateAverageVotes(media.getVotes()))
                .build();
    }

    private double calculateAverageRating(List<Rating> ratings) {
        return ratings.stream()
                .mapToDouble(Rating::getValue)
                .average()
                .orElse(0.0);
    }

    private double calculateAverageVotes(List<Votes> ratings) {
        return ratings.stream()
                .mapToDouble(Votes::getValue)
                .average()
                .orElse(0.0);
    }
}