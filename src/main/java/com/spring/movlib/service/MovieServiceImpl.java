package com.spring.movlib.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.movlib.dao.MovieRepository;
import com.spring.movlib.model.Movie;

@Component
public class MovieServiceImpl implements MovieService {
	
	private MovieRepository movieRepository;
	
	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public Movie addMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	public Iterable<Movie> read() {
		return movieRepository.findAll();
	}
	
	@Override
	public Movie update(Movie movie) {
		if (movieRepository.findById(movie.getId()).isPresent())
			return addMovie(movie);
		else
			return null;
	}

	@Override
	public void deleteMovieById(Integer id) {
		movieRepository.deleteById(id);
	}

	@Override
	public Optional<Movie> findMovieById(Integer id) {
		return movieRepository.findById(id);
	}
	
	@Override
	public List<Movie> findMoviesByName(String name) {
		return (List<Movie>) movieRepository.findByName(name);
	}

	@Override
	public List<Movie> findMoviesByDirector(String director) {
		return (List<Movie>) movieRepository.findByDirector(director);
	}

	@Override
	public List<Movie> findAllMovies() {
		return (List<Movie>) movieRepository.findAll();
	}

	@Override
	public Iterable<Movie> findMoviesByCategory(String categoryName) {
		Iterable<Integer> movieIds = movieRepository.getMoviesByCategory(categoryName);
		return movieRepository.findAllById(movieIds);
	}

}
