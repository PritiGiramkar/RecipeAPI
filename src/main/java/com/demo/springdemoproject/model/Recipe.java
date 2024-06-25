package com.demo.springdemoproject.model;

import javax.persistence.*;
import java.net.URL;

@Entity
@Table(name = "Recipe")
public class Recipe {
	@Id	// this is primary key
	@GeneratedValue
	private long id;
	@Column(name = "title", length = 30)
	private String title;
	@Column(name = "image")
	private String image;
	@Column(name = "sourceUrl")
	private URL sourceUrl;
	@Column(name = "servings",columnDefinition = "integer default 1")
	private Integer servings;
	@Column(name = "extendedIngredients")
	private String extendedIngredients[];
	@Column(name = "steps")
	private String steps[];
	
			//it will y default add the id to the value
	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public URL getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(URL sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public Integer getServings() {
		return servings;
	}

	public void setServings(Integer servings) {
		this.servings = servings;
	}

	public String[] getExtendedIngredients() {
		return extendedIngredients;
	}

	public void setExtendedIngredients(String[] extendedIngredients) {
		this.extendedIngredients = extendedIngredients;
	}

	public String[] getSteps() {
		return steps;
	}

	public void setSteps(String[] steps) {
		this.steps = steps;
	}
}
