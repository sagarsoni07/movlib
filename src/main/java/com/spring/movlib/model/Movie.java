package com.spring.movlib.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank
	private String name;
	private String director;
	private String releaseYear;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "movie", orphanRemoval = true)
	@Column(name = "category_name")
	private Set<Category> categories;
	private String description;

	public Movie() {
	}

	public Movie(@NotBlank String name, String director, String releaseYear, Set<Category> categories,
			String description) {
		this.name = name;
		this.director = director;
		this.releaseYear = releaseYear;
		this.categories = categories;
		this.description = description;
	}
	public Movie(int id, @NotBlank String name, String director, String releaseYear, Set<Category> categories,
			String description) {
		this.id = id;
		this.name = name;
		this.director = director;
		this.releaseYear = releaseYear;
		this.categories = categories;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
