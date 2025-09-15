package dev.kauan.api;

import java.util.Arrays;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.kauan.api.model.Movie;
import dev.kauan.api.repository.MovieRepository;

@SpringBootApplication
public class Api {

	public static void main(String[] args) {
		SpringApplication.run(Api.class, args);
	}

	@Bean
	ApplicationRunner runner(MovieRepository repository) {
		return _ -> {
			var movies = Arrays.asList(
					new Movie("Inception", "Christopher Nolan", 2010,
							"A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O."),
					new Movie("The Matrix", "The Wachowskis", 1999,
							"A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers."),
					new Movie("Interstellar", "Christopher Nolan", 2014,
							"A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival."),
					new Movie("The Godfather", "Francis Ford Coppola", 1972,
							"The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son."),
					new Movie("Pulp Fiction", "Quentin Tarantino", 1994,
							"The lives of two mob hitmen, a boxer, a gangster's wife, and a pair of diner bandits intertwine in four tales of violence and redemption."));

			repository.saveAll(movies);
		};
	}

}
