package dev.kauan.leadcadora_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.kauan.leadcadora_api.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
