package com.dmitriyg.authMeetsCrud.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "businesses")
public class Business {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private LocalDate date;

    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name="user_id")
    private User user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="business", cascade={CascadeType.MERGE}) 
    private List<Review> reviews;

	public Business() {
	}

	public Business(String name, String description, LocalDate date, User user, List<Review> reviews) {
		this.name = name;
		this.description = description;
		this.date = date;
		this.user = user;
		this.reviews = reviews;
	}

	public Business(Long id, String name, String description, LocalDate date, User user, List<Review> reviews) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.date = date;
		this.user = user;
		this.reviews = reviews;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "Business [id=" + id + ", name=" + name + ", description=" + description + ", date=" + date + ", user="
				+ user + "]";
	}

}
