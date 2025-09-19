package dev.kauan.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import dev.kauan.api.domain.dto.AuthRequest;
import dev.kauan.api.service.AuthService;

record AuthResponse(String token) {
}

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody AuthRequest request) {
        return authService.getTokenFromCredentials(request.name(), request.password())
                .map((token) -> new AuthResponse(token))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
