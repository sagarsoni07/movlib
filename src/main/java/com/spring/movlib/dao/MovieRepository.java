package com.spring.movlib.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.movlib.model.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer>{

	List<Movie> findByName(String name);
	List<Movie> findByDirector(String director);
	
	@Query("SELECT m.movie_id FROM movie_category m WHERE m.category_name = ?1")
	Iterable<Integer> getMoviesByCategory(String categoryName);
}
