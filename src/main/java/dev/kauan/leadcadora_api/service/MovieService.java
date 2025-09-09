package dev.kauan.leadcadora_api.service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.kauan.leadcadora_api.entity.Movie;
import dev.kauan.leadcadora_api.repository.MovieRepository;
import dev.kauan.leadcadora_api.request.MovieRequest;

@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie createMovie(MovieRequest request) {
        var newMovie = new Movie(request.title(), request.director(), request.releaseYear(), request.synopsis());
        return movieRepository.save(newMovie);
    }

    @Transactional(readOnly = true)
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Movie> getMovieFromId(Long id) {
        return movieRepository.findById(id);
    }

    public Movie updateMovie(Long id, MovieRequest request) {
        if (!movieRepository.existsById(id)) {
            throw new NoSuchElementException();
        }

        var updatedMovie = new Movie(request.title(), request.director(), request.releaseYear(),
                request.synopsis());
        return movieRepository.save(updatedMovie);
    }

    public Movie partialUpdateMovie(Long id, Map<String, Object> updates) {
        return movieRepository.findById(id)
                .map(movie -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "title" -> movie.setTitle((String) value);
                            case "director" -> movie.setDirector((String) value);
                            case "releaseYear" -> movie.setReleaseYear((Integer) value);
                            case "synopsis" -> movie.setSynopsis((String) value);
                        }
                    });
                    return movieRepository.save(movie);
                })
                .orElseThrow(() -> new NoSuchElementException());
    }

    public void deleteMovieById(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new NoSuchElementException();
        }

        movieRepository.deleteById(id);
    }
}
