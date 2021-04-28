package com.spring.movlib.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.movlib.dao.CategoryRepository;
import com.spring.movlib.model.Category;
import com.spring.movlib.model.CategoryName;
import com.spring.movlib.model.Movie;


@Component
public class CategoryServiceImpl implements CategoryService {
	
private CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@Override
	public Iterable<Movie> findMoviesByCategory(CategoryName categoryName) {
		 Iterable<Category> movieCategories = categoryRepository.findByCategoryName(categoryName);
		 Set<Movie> moviesByCategory = new HashSet<Movie>();
		 for(Category category: movieCategories) {
			 moviesByCategory.add(category.getMovie());
		 }
		 return moviesByCategory;
	}


}
