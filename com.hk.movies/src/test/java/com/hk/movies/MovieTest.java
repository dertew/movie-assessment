package com.hk.movies;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.movies.model.Movie;
import com.hk.movies.repository.MovieRepository;

@SpringBootTest
public class MovieTest {

	@Autowired
	MovieRepository MovieRepository;

	
	@Test
	public void shouldReturnCreatedMovie() {
		Movie movie = new Movie();
		movie.setTitle("Test title");
		movie.setCategory("Comedy");
		movie.setRating(1);
		Movie movieDto = MovieRepository.save(movie);		
		Optional<Movie> movieTest = MovieRepository.findById(movieDto.getId());

		assertThat(movieTest).isPresent();
		movieTest.ifPresent(dto -> {
			assertThat(dto).extracting(Movie::getTitle).isEqualTo("Test title");
			assertThat(dto).extracting(Movie::getCategory).isEqualTo("Comedy");
			assertThat(dto).extracting(Movie::getRating).isEqualTo(1);
		});

	}
	@Test
	public void shouldDeleteMovie() {
		Movie movie = new Movie();
		movie.setTitle("Test title test delete");
		movie.setCategory("Comedy");
		movie.setRating(2);
		Movie movieDto = MovieRepository.save(movie);		
		Optional<Movie> movieTest = MovieRepository.findById(movieDto.getId());
		MovieRepository.deleteById(movieDto.getId());
		Optional<Movie> moviePostDeleteTest = MovieRepository.findById(movieDto.getId());
		assertThat(moviePostDeleteTest).isPresent();
		movieTest.ifPresent(dto -> {
			assertThat(dto).extracting(Movie::getTitle).isEqualTo("Test title");
			assertThat(dto).extracting(Movie::getCategory).isEqualTo("Comedy");
			assertThat(dto).extracting(Movie::getRating).isEqualTo(1);
		});

	}
	@Test
	public void shouldReturnNullForNotExistingPost() {
		Optional<Movie> movieDto = MovieRepository.findById(999L);

		assertThat(movieDto).isEmpty();
	}
	
	
	@Test
	public void shouldRejectRatingBelowRange() {
		Movie movie = new Movie();
		movie.setTitle("Test title2");
		movie.setCategory("Comedy");
		movie.setRating(0);
		Movie movieDto = MovieRepository.save(movie);		
		Optional<Movie> movieTest = MovieRepository.findById(movieDto.getId());
		assertThat(movieTest).isEmpty();

	}

	@Test
	public void shouldRejectRatingBeyondRange() {
		Movie movie = new Movie();
		movie.setTitle("Test title3");
		movie.setCategory("Comedy");
		movie.setRating(11);
		Movie movieDto = MovieRepository.save(movie);		
		Optional<Movie> movieTest = MovieRepository.findById(movieDto.getId());
		assertThat(movieTest).isEmpty();

	}

}
