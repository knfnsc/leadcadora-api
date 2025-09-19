package dev.kauan.api.domain.dto;

import java.util.Optional;

public record UserResponse(Long id, String name, String password, Boolean isAdmin, Optional<Long> favoriteId) {

}
