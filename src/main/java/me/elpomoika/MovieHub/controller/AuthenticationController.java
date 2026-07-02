package me.elpomoika.MovieHub.controller;

import lombok.RequiredArgsConstructor;
import me.elpomoika.MovieHub.dto.authentication.AuthResponse;
import me.elpomoika.MovieHub.dto.authentication.LoginRequest;
import me.elpomoika.MovieHub.dto.authentication.RefreshRequest;
import me.elpomoika.MovieHub.security.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshRequest request) {
        return authService.refresh(request);
    }
}
