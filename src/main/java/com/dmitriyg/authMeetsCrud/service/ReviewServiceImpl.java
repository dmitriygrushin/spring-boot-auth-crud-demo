package com.dmitriyg.authMeetsCrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmitriyg.authMeetsCrud.model.Review;
import com.dmitriyg.authMeetsCrud.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public void save(Review review) {
		reviewRepository.save(review);
	}

	@Override
	public Optional<Review> findById(int id) {
		return reviewRepository.findById(id);
	}

	@Override
	public void deleteById(int id) {
		reviewRepository.deleteById(id);
	}

	@Override
	public List<Review> findAllByBusinessId(int id) {
		return reviewRepository.findAllByBusinessId(id);
	}

	@Override
	public boolean userReviewedBusiness(int userId, int businessId) {
		return reviewRepository.countByUserIdAndBusinessId(userId, businessId) == 0;
	}

	@Override
	public boolean checkIfUserOwnsReview(int userId, Review review) {
		// new review so, no need to check
		// checking if 0 instead of null unlike in business is because review id: int, business id: long 
		// should change this later but it's not essential right now
		if (review.getId() == 0) return true; 
		
		
		// if there is 1 count of review by the reviewId that has a userId then the userId owns this review
		return reviewRepository.countByUserIdAndId(userId, review.getId()) == 1;
	}
	
	


}
