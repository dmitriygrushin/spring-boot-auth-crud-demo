package com.dmitriyg.authMeetsCrud.service;

import java.util.List;
import java.util.Optional;

import com.dmitriyg.authMeetsCrud.model.Review;

public interface ReviewService {

	void save(Review review);

	void deleteById(int id);

	Optional<Review> findById(int id);
	
	List<Review> findAllByBusinessId(int id);
	
	boolean userReviewedBusiness(int userId, int businessId);
	
	boolean checkIfUserOwnsReview(int userId, Review review);

}