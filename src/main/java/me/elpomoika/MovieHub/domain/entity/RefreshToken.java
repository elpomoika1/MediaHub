package me.elpomoika.MovieHub.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class RefreshToken {
    @Id
    private Long id;

    private String token;
    private Instant expiresAt;
    private boolean revoked;

    @ManyToOne
    private User user;
}
