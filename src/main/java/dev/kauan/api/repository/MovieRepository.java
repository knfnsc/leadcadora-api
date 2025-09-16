package dev.kauan.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.kauan.api.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, UUID> {
}
