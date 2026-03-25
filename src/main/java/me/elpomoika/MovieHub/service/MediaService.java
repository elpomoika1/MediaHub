package me.elpomoika.MovieHub.service;

import lombok.RequiredArgsConstructor;
import me.elpomoika.MovieHub.domain.dto.MediaRequest;
import me.elpomoika.MovieHub.domain.entity.Media;
import me.elpomoika.MovieHub.domain.entity.Rating;
import me.elpomoika.MovieHub.domain.enums.MediaType;
import me.elpomoika.MovieHub.repository.MediaRepository;
import me.elpomoika.MovieHub.util.SlugGenerator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MediaService {
    private final MediaRepository mediaRepository;
    private final S3FileStorageService s3StorageService;

    public void saveMovie(MultipartFile file, MediaRequest request) throws IOException {
        String title = request.getTitle();
        Media media = Media.builder()
                .title(title)
                .type(request.getType())
                .genres(request.getGenres())
                .build();

        media = mediaRepository.save(media);

        final String slug = SlugGenerator.generateSlug(title) + "-" + media.getId();
        final String imageUrl = s3StorageService.uploadFile(file, slug);

        media.setName(slug);
        media.setImageUrl(imageUrl);

        mediaRepository.save(media);
    }

    public void rateMedia(String name, double inputRating) {
        if (inputRating > 10 || inputRating <= 0) return;

        Media media = mediaRepository.findByName(name);
        if (media == null) return;

        Rating rating = Rating.builder()
                .media(media)
                .value(inputRating)
                .build();

        media.getRating().add(rating);
        mediaRepository.save(media);
    }

    public Media getRandomMovie() {
        final Random random = new Random();
        List<Media> media = getMedias();

        return media.get(random.nextInt(media.size()));
    }

    public Media getMediaBySlug(String slug) {
        return mediaRepository.findByName(slug);
    }

    public List<Media> getMedias() {
        return mediaRepository.findAll();
    }

    public List<Media> getMediasByType(MediaType mediaType) {
        return getMedias().stream()
                .filter(m -> m.getType() == mediaType)
                .toList();
    }
}