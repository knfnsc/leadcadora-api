package dev.kauan.leadcadora_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.kauan.leadcadora_api.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
