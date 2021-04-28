package com.spring.movlib.dao;

import org.springframework.data.repository.CrudRepository;

import com.spring.movlib.model.Category;
import com.spring.movlib.model.CategoryName;

public interface CategoryRepository extends CrudRepository<Category, Integer>{

	Iterable<Category> findByCategoryName(CategoryName categoryName);

}
