package com.spring.movlib.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spring.movlib.model.Movie;

@Service
public interface MovieService {
	
	public Movie addMovie(Movie movie);
	public Iterable<Movie> read();
	public Movie update( Movie movie);
	public void deleteMovieById(Integer id);
	public Optional<Movie> findMovieById(Integer id);
	List<Movie> findMoviesByName(String name);
	List<Movie> findMoviesByDirector(String name);
	List<Movie> findAllMovies();
	Iterable<Movie> findMoviesByCategory(String categoryName);
	
}