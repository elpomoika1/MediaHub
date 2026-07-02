package me.elpomoika.MovieHub.dto.authentication;

import lombok.Data;

import java.util.UUID;

@Data
public class UserCredentialsDto {
    private UUID userId;
    private String email;
}
