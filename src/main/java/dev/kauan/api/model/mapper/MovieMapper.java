package dev.kauan.api.model.mapper;

import org.mapstruct.Mapper;

import dev.kauan.api.dto.MovieRequest;
import dev.kauan.api.dto.MovieResponse;
import dev.kauan.api.model.Movie;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieResponse entityToResponse(Movie movie);

    Movie requestToEntity(MovieRequest movieRequest);
}
