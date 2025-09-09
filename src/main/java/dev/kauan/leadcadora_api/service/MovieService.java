package dev.kauan.leadcadora_api.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.kauan.leadcadora_api.model.Movie;
import dev.kauan.leadcadora_api.repository.MovieRepository;

@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public void createMovie(Movie movie) {
        this.movieRepository.save(movie);
    }

    @Transactional(readOnly = true)
    public List<Movie> getAllMovies() {
        return this.movieRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Movie> getMovieFromId(Long id) {
        return this.movieRepository.findById(id);
    }

    public Movie updateMovie(Movie movie) {
        if (!this.doesMovieExist(movie.getId())) {
            throw new NoSuchElementException();
        }

        this.movieRepository.save(movie);
        return movie;
    }

    private Boolean doesMovieExist(Long id) {
        return this.movieRepository.existsById(id);
    }
}
