package dev.kauan.api.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.kauan.api.domain.dto.MovieRequest;
import dev.kauan.api.domain.dto.MovieResponse;
import dev.kauan.api.domain.mapper.MovieMapper;
import dev.kauan.api.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getMovies() {
        return ResponseEntity.ok(
                movieService.getAllMovies()
                        .stream()
                        .map(movieMapper::entityToResponse)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable UUID id) {
        return movieService.getMovieFromId(id)
                .map(movieMapper::entityToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MovieResponse> createMovie(@Valid @RequestBody MovieRequest request) {
        var createdMovie = movieService.createMovie(movieMapper.requestToEntity(request));
        var response = movieMapper.entityToResponse(createdMovie);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable UUID id, @Valid @RequestBody MovieRequest request) {
        return movieService.updateMovie(id, movieMapper.requestToEntity(request))
                .map(movieMapper::entityToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable UUID id) {
        return movieService.deleteMovieById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
