package com.spring.movlib;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.spring.movlib.controller.MovieController;
import com.spring.movlib.model.Category;
import com.spring.movlib.model.CategoryName;
import com.spring.movlib.model.Movie;
import com.spring.movlib.service.CategoryService;
import com.spring.movlib.service.MovieService;


@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovlibApplicationTests {
	
	@MockBean
	private MovieService movieService;
	
	@MockBean
	private CategoryService categoryService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test()
	public void testlistAllMoviesShouldReturnListOfMovies() throws Exception{
		Movie movie = new Movie("Gordon","Moore", null, null, null);
		List<Movie> movies = Arrays.asList((movie));
		Mockito.when(movieService.read()).thenReturn(movies);
		mockMvc.perform(get("/movie")).andExpect(status().isOk()).
		andExpect(jsonPath("$",Matchers.hasSize(1))).
		andExpect(jsonPath("$[0].name", Matchers.is("Gordon")));
	}
	
	@Test()
	public void testfindByIdShouldReturnMovie() throws Exception{
		Optional<Movie> movie = Optional.of(new Movie("Gordon","Moore", null, null, null));
		Mockito.when(movieService.findMovieById(1)).thenReturn(movie);
		mockMvc.perform(get("/movie/1")).andExpect(status().isOk()).
		andExpect(jsonPath("$.name", Matchers.is("Gordon")));
	}
	
	@Test()
	public void testlistMoviesByCategoryShouldReturnMovies() throws Exception{
		Category category = new Category();
		category.setCategoryName(CategoryName.THRILLER);
		Set<Category> categories = new HashSet<>();
		Movie movie = new Movie("Gordon","Moore", null, categories, null);
		List<Movie> movies = Arrays.asList((movie));
		Mockito.when(categoryService.findMoviesByCategory(category.getCategoryName())).thenReturn(movies);
		mockMvc.perform(get("/category/THRILLER")).andExpect(status().isOk()).
		andExpect(jsonPath("$",Matchers.hasSize(1))).
		andExpect(jsonPath("$[0].name", Matchers.is("Gordon")));
	}
	
}
