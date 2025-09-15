package dev.kauan.api.dto;

import java.util.UUID;

public record MovieResponse(UUID id, String title, String director, Integer releaseYear, String synopsis) {
}
