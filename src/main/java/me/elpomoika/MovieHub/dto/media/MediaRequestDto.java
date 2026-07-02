package me.elpomoika.MovieHub.dto.media;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.elpomoika.MovieHub.domain.enums.Genre;
import me.elpomoika.MovieHub.domain.enums.MediaType;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class MediaRequestDto {
    private final String title;
    private final MediaType type;
    private final List<Genre> genres;
}
