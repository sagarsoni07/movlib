package com.spring.movlib.service;

import com.spring.movlib.model.CategoryName;
import com.spring.movlib.model.Movie;

public interface CategoryService {

	Iterable<Movie> findMoviesByCategory(CategoryName categoryName);

}