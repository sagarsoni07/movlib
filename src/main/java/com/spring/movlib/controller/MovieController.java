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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.spring.movlib.model.CategoryName;
import com.spring.movlib.model.Movie;
import com.spring.movlib.service.CategoryService;
import com.spring.movlib.service.MovieService;

@RestController
public class MovieController {

	private MovieService movieService;
	private CategoryService categoryService;

	@Autowired
	public MovieController(MovieService movieService, CategoryService categoryService) {
		this.movieService = movieService;
		this.categoryService = categoryService;
	}

	@PostMapping("/movie")
	Movie addMovie(@Valid @RequestBody Movie movie) throws ValidationException {
		if(movie.getId() != 0)
			throw new ValidationException("Id can not be pre-defined");
		return movieService.addMovie(movie);
	}
	 

	@GetMapping("/movie")
	Iterable<Movie> listAllMovies() {
		Iterable<Movie> foundMovies = movieService.read();
		if(foundMovies == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Movies not Found");
		return foundMovies;
	}
	
	@PutMapping("/movie")
	ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) {
		Movie updatedMovie = movieService.update(movie);
		if ( updatedMovie == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Movie Request/Movie Not Present");
		return new ResponseEntity<Movie>(updatedMovie, HttpStatus.OK);
	}
	
	@DeleteMapping("/movie/{id}")
	void delete(@PathVariable Integer id) {
		if(movieService.findMovieById(id).isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Movie not Found");
		movieService.deleteMovieById(id);
	}

	@GetMapping("/movie/{id}")
	Optional<Movie> findById(@PathVariable Integer id) {
		Optional<Movie> foundMovie = movieService.findMovieById(id);
		if(foundMovie.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Movie not Found");
		return foundMovie;
	}
	
	@GetMapping("/category/{categoryName}")
	Iterable<Movie> listMoviesByCategory(@PathVariable CategoryName categoryName) {
		Iterable<Movie> foundMovies = categoryService.findMoviesByCategory(categoryName);
		if(foundMovies == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Movies Found for the Category: "+ categoryName);
		return foundMovies;
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

}
