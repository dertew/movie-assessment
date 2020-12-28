package com.hk.movies.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author hktew Entity to create persist data into database
 */
/**
 * @author hktew
 *
 */
/**
 * @author hktew
 *
 */
/**
 * @author hktew
 *
 */
/**
 * @author hktew
 *
 */
/**
 * @author hktew
 *
 */
/**
 * @author hktew
 *
 */
@Entity
@Table(name = "movies")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "category")
	private String category;

	@Column(name = "rating")
	private float rating;

	public Movie() {

	}

	/**
	 * @param title
	 * @param category
	 * @param rating
	 */
	public Movie(String title, String category, float rating) {
		this.title = title;
		this.category = category;
		this.rating = rating;
	}

	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return
	 */
	public String getCategory() {
		return category; 
	}

	/**
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return Ratings returns rating of the movie
	 */
	@Min(value=1, message="Minimum is 0.5")
	@Max(value=5, message="Maximum is 5")
	
	public float getRating() {
		return rating;
	}

	/**
	 * @param rating rating of the movie
	 */
	public void setRating(float rating) {
		this.rating = rating;
	}
	
	
	/**
	 * @return id ID of movie
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Used to display all fields in one string
	 */
	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", title=" + title + ", cat=" + category + ", rating=" + rating + "]";
	}

}
