package dev.kauan.api;

import java.util.Arrays;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.kauan.api.domain.Movie;
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
					new Movie("The Matrix", "The Wachowskis", 1999,
							"A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
							"https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%2Fid%2FOIP.mCr3x90hubrByxx2xp21EwHaLH%3Fpid%3DApi&f=1&ipt=5ebc5a97a0b70d882d4635f1880a688f464987b011e3c13ac4f86b1802935dd2&ipo=images"),
					new Movie("The Godfather", "Francis Ford Coppola", 1972,
							"The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
							"https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%2Fid%2FOIP.bc6LFsSyD8iNMydB7FVr9AHaKg%3Fr%3D0%26pid%3DApi&f=1&ipt=8428d306619747a55bc3e605ad7cbd860a705830f9fd57e0ec6bbb1a57fb2934&ipo=images"),
					new Movie("Pulp Fiction", "Quentin Tarantino", 1994,
							"The lives of two mob hitmen, a boxer, a gangster's wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
							"https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.explicit.bing.net%2Fth%2Fid%2FOIP.lJ1SALoAkquFWQ_RZcLteQHaLH%3Fpid%3DApi&f=1&ipt=b06e3ea74f8889a930f51498d5119b539fde4e020ce6c902324728a09c1f7ad1&ipo=images"));

			repository.saveAll(movies);
		};
	}

}
