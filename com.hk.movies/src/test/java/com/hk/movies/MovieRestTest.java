package com.hk.movies;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hk.movies.controller.MovieController;
import com.hk.movies.model.Movie;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MovieController.class)
public class MovieRestTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private MovieController movieController;

	@MockBean
	private Movie newMovieDto;

	
	@Test
	public void shouldAddMovie() throws Exception {

		// given
		Movie newMovie = createMovies("Test title", "Romance", 3);

		// then
		mockMvc.perform(post("/api/moviess").content(objectMapper.writeValueAsBytes(newMovie))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldReturnFoundMovies() throws Exception {

		// given
		 Movie movieTest = createMovies("Title 2", "Sci-Fi", 2);

		 ResponseEntity<Movie> respMovie = movieController.createMovie(movieTest);
		 
		// then
		mockMvc.perform(get("/api/movies/" + respMovie.getBody().getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].id", is(respMovie.getBody().getId())))
				.andExpect(jsonPath("$[0].title", is("Title 2"))).andExpect(jsonPath("$[0].category", is("Sci-Fi")))
				.andExpect(jsonPath("$[0].rating", is(2)));
		
		movieController.deleteMovie(respMovie.getBody().getId());
	}

	private Movie createMovies(String title, String category, long rating) {
		Movie newMovie = new Movie();
		newMovie.setTitle(title);
		newMovie.setCategory(category);
		newMovie.setRating(rating);
		return newMovie;
	}

	@Test
	public void shouldDeletMovie() throws Exception {

		// given
		Movie newMovie = createMovies("Test title 4", "Romance", 4);
		// then
		mockMvc.perform(post("/api/movies").content(objectMapper.writeValueAsBytes(newMovie))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		// then
	    this.mockMvc.perform(delete("/api/movies/"+newMovie.getId()).content(objectMapper.writeValueAsBytes(newMovie))
		.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

	}

}
