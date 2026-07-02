package me.elpomoika.MovieHub.dto.authentication;

import lombok.Data;

@Data
public class RefreshRequest {
    private String refreshToken;
}
