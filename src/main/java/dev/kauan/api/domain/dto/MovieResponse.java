package dev.kauan.api.domain.dto;

public record MovieResponse(Long id, String title, String director, Integer releaseYear, String synopsis,
        String posterURL) {
}
