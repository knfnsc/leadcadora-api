package dev.kauan.leadcadora_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.kauan.leadcadora_api.model.Movie;
import dev.kauan.leadcadora_api.service.MovieService;
import dev.kauan.leadcadora_api.request.MovieRequest;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody MovieRequest request) {
        var movie = new Movie(null, request.title(), request.director(), request.releaseDate(), request.synopsis());
        this.movieService.createMovie(movie);
        return ResponseEntity.ok(movie);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        var movies = this.movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        var movie = this.movieService.getMovieFromId(id);
        return movie.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie updatedMovie) {
        this.movieService.updateMovie(updatedMovie);
        return ResponseEntity.ok(updatedMovie);
    }
}
