package me.elpomoika.MovieHub.domain.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class Rating extends BaseValue {
}