package me.elpomoika.MovieHub.security;

import lombok.RequiredArgsConstructor;
import me.elpomoika.MovieHub.domain.entity.RefreshToken;
import me.elpomoika.MovieHub.domain.entity.User;
import me.elpomoika.MovieHub.dto.authentication.AuthResponse;
import me.elpomoika.MovieHub.dto.authentication.LoginRequest;
import me.elpomoika.MovieHub.dto.authentication.RefreshRequest;
import me.elpomoika.MovieHub.repository.UserRepository;
import me.elpomoika.MovieHub.security.jwt.JwtService;
import me.elpomoika.MovieHub.security.jwt.RefreshTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        String accessToken = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.create(user);

        return new AuthResponse(accessToken, refreshToken.getToken());
    }

    public AuthResponse refresh(RefreshRequest request) {
        RefreshToken stored = refreshTokenService.validate(request.getRefreshToken());

        User user = stored.getUser();
        String newAccessToken = jwtService.generateToken(user);

        return new AuthResponse(newAccessToken, stored.getToken());
    }
}
