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
	
	


}
