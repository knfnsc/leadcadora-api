package dev.kauan.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.kauan.api.domain.Movie;
import dev.kauan.api.repository.MovieRepository;

@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie createMovie(Movie movie) {
        var newMovie = new Movie(movie.getTitle(), movie.getDirector(), movie.getReleaseYear(), movie.getSynopsis(),
                movie.getPosterURL());
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

    public Optional<Movie> updateMovie(Long id, Movie movie) {
        return movieRepository.findById(id)
                .map(existingMovie -> {
                    existingMovie.setTitle(movie.getTitle());
                    existingMovie.setDirector(movie.getDirector());
                    existingMovie.setReleaseYear(movie.getReleaseYear());
                    existingMovie.setSynopsis(movie.getSynopsis());
                    return movieRepository.save(existingMovie);
                });
    }

    public Boolean deleteMovieById(Long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
