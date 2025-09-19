package dev.kauan.api.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(
                @NotBlank @Size(min = 8, max = 255) String name,
                @NotBlank @Size(min = 8, max = 255) String password) {
}
