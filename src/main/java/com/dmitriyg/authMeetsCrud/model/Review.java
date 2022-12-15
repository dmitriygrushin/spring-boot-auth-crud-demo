package com.dmitriyg.authMeetsCrud.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String description;
	
	private int stars;
	
	private LocalDate date;
	
    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name="user_id")
	private User user;
	
    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name="business_id")
	private Business business;
    
    public Review() {
    }

	public Review(String description, int stars, LocalDate date, User user, Business business) {
		this.description = description;
		this.stars = stars;
		this.date = date;
		this.user = user;
		this.business = business;
	}

	public Review(int id, String description, int stars, LocalDate date, User user, Business business) {
		this.id = id;
		this.description = description;
		this.stars = stars;
		this.date = date;
		this.user = user;
		this.business = business;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

}
