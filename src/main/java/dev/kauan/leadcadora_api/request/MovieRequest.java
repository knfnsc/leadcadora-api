package dev.kauan.leadcadora_api.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MovieRequest(
                @NotBlank(message = "Title cannot be blank") @Size(max = 100, message = "Title cannot exceed 100 characters") String title,
                @NotBlank(message = "Director cannot be blank") @Size(max = 100, message = "Director name cannot exceed 100 characters") String director,
                @NotNull(message = "Release year cannot be null") @Min(value = 1888, message = "Release year must be 1888 or later") Integer releaseYear,
                @NotBlank(message = "Synopsis cannot be blank") @Size(max = 255, message = "Synopsis cannot exceed 255 characters") String synopsis) {
}
