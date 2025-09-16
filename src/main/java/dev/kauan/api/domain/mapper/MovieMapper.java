package dev.kauan.api.domain.mapper;

import org.mapstruct.Mapper;

import dev.kauan.api.domain.Movie;
import dev.kauan.api.domain.dto.MovieRequest;
import dev.kauan.api.domain.dto.MovieResponse;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieResponse entityToResponse(Movie movie);

    Movie requestToEntity(MovieRequest movieRequest);
}
