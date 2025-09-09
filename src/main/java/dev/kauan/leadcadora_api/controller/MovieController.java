package dev.kauan.leadcadora_api.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.kauan.leadcadora_api.service.MovieService;
import dev.kauan.leadcadora_api.entity.Movie;
import dev.kauan.leadcadora_api.request.MovieRequest;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        var movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        var movie = movieService.getMovieFromId(id);
        return movie.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody MovieRequest request) {
        var createdMovie = movieService.createMovie(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody MovieRequest request) {
        try {
            var updatedMovie = movieService.updateMovie(id, request);
            return ResponseEntity.ok(updatedMovie);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Movie> partialUpdateMovie(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        try {
            var updatedMovie = movieService.partialUpdateMovie(id, updates);
            return ResponseEntity.ok(updatedMovie);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        try {
            movieService.deleteMovieById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
