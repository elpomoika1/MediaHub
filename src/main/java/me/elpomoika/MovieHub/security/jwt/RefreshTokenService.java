package me.elpomoika.MovieHub.security.jwt;

import lombok.RequiredArgsConstructor;
import me.elpomoika.MovieHub.domain.entity.RefreshToken;
import me.elpomoika.MovieHub.domain.entity.User;
import me.elpomoika.MovieHub.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken create(User user) {
        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiresAt(
                Instant.now().plus(7, ChronoUnit.DAYS)
        );
        token.setRevoked(false);

        return refreshTokenRepository.save(token);
    }

    public RefreshToken validate(String token) {
        return refreshTokenRepository.findByToken(token)
                .filter(t -> !t.isRevoked())
                .filter(t -> t.getExpiresAt().isAfter(Instant.now()))
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));
    }
}
