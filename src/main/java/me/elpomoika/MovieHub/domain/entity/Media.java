package me.elpomoika.MovieHub.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import me.elpomoika.MovieHub.domain.enums.Genre;
import me.elpomoika.MovieHub.domain.enums.MediaType;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private MediaType type;

    @ElementCollection(targetClass = Genre.class)
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;

    private String title;

    @Column(length = 2048)
    private String imageUrl;

    @OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
    private final List<Rating> rating = new ArrayList<>();

    @OneToMany(mappedBy = "media", cascade = CascadeType.ALL)
    private final List<Votes> votes = new ArrayList<>();
}
