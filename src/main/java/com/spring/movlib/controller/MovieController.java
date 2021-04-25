package com.spring.movlib.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.movlib.model.Movie;
import com.spring.movlib.service.MovieService;

@RestController
public class MovieController {

	private MovieService movieService;

	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@PostMapping("/movie")
	Movie addMovie(@Valid @RequestBody Movie movie) throws ValidationException {
		return movieService.addMovie(movie);
	}
	 

	@GetMapping("/movie")
	Iterable<Movie> listAllMovies() {
		return movieService.read();
	}
	
	@GetMapping("/category/{categoryName}")
	Iterable<Movie> listMoviesByCategory(@PathVariable String categoryName) {
		return movieService.findMoviesByCategory(categoryName);
	}

	@PutMapping("/movie")
	ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) {
		Movie updatedMovie = movieService.update(movie);
		if ( updatedMovie != null)
			return new ResponseEntity<Movie>(updatedMovie, HttpStatus.OK);
		else
			return new ResponseEntity<Movie>(movie, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/movie/{id}")
	void delete(@PathVariable Integer id) {
		movieService.deleteMovieById(id);
	}

	@GetMapping("/movie/{id}")
	Optional<Movie> findById(@PathVariable Integer id) {
		return movieService.findMovieById(id);
	}

	@GetMapping("/movie/search")
	Iterable<Movie> searchMovies(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "director", required = false) String director) {
		if (name != null && director != null) {
			List<Movie> FoundMovies = new ArrayList<Movie>() ;
			Stream.of(movieService.findMoviesByName(name), movieService.findMoviesByDirector(director)).
					forEach(FoundMovies :: addAll);
			return FoundMovies;
		}
		else if (name != null)
			return movieService.findMoviesByName(name);
		else if (director != null)
			return movieService.findMoviesByDirector(director);
		else
			return movieService.findAllMovies();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	String exceptionHandler(ValidationException e) {
		return e.getMessage();
	}

}
