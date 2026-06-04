package me.elpomoika.MovieHub.mapper;

import me.elpomoika.MovieHub.domain.dto.MediaPreviewDTO;
import me.elpomoika.MovieHub.domain.entity.Media;
import me.elpomoika.MovieHub.util.ArithmeticMeanCalculator;
import org.springframework.stereotype.Service;

@Service
public class MediaMapper {
    public MediaPreviewDTO toDto(Media media) {
        return MediaPreviewDTO.builder()
                .name(media.getName())
                .title(media.getTitle())
                .imageUrl(media.getImageUrl())
                .rating(ArithmeticMeanCalculator.calculateAverage(media.getRating()))
                .votes(ArithmeticMeanCalculator.calculateAverage(media.getVotes()))
                .build();
    }
}