package dev.kauan.api.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.kauan.api.model.Movie;
import dev.kauan.api.repository.MovieRepository;

@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie createMovie(Movie movie) {
        var newMovie = new Movie(movie.getTitle(), movie.getDirector(), movie.getReleaseYear(), movie.getSynopsis());
        return movieRepository.save(newMovie);
    }

    @Transactional(readOnly = true)
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Movie> getMovieFromId(UUID id) {
        return movieRepository.findById(id);
    }

    public Optional<Movie> updateMovie(UUID id, Movie movie) {
        return movieRepository.findById(id)
                .map(existingMovie -> {
                    existingMovie.setTitle(movie.getTitle());
                    existingMovie.setDirector(movie.getDirector());
                    existingMovie.setReleaseYear(movie.getReleaseYear());
                    existingMovie.setSynopsis(movie.getSynopsis());
                    return movieRepository.save(existingMovie);
                });
    }

    public void deleteMovieById(UUID id) {
        if (!movieRepository.existsById(id)) {
            throw new NoSuchElementException();
        }

        movieRepository.deleteById(id);
    }
}
