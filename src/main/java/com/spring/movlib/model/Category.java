package com.spring.movlib.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryid;
	private CategoryName categoryName;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	private Movie movie;
	
	public Category() {
	}

	public Category(int categoryid, CategoryName categoryName, Movie movie) {
		this.categoryid = categoryid;
		this.categoryName = categoryName;
		this.movie = movie;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public CategoryName getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(CategoryName categoryName) {
		this.categoryName = categoryName;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
}
