package me.elpomoika.MovieHub.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String title;
    @Column(length = 2048)
    private String imageUrl;
    private double rating;
    private double votes;
}
