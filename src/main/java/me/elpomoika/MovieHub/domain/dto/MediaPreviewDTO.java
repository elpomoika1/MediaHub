package me.elpomoika.MovieHub.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MediaPreviewDTO {
    private String name;
    private String title;
    private String imageUrl;
    private double rating;
    private double votes;
}
