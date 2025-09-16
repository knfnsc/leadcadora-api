package dev.kauan.api.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MovieRequest(
        @NotBlank @Size(min = 1, max = 100) String title,
        @NotBlank @Size(min = 1, max = 100) String director,
        @NotNull @Min(value = 1888) Integer releaseYear,
        @NotBlank @Size(min = 1, max = 200) String synopsis) {
}
