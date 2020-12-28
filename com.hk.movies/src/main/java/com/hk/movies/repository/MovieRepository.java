package com.hk.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hk.movies.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	List<Movie> findByTitleContaining(String title);	
	
}
