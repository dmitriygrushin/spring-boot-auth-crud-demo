package com.dmitriyg.authMeetsCrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmitriyg.authMeetsCrud.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	List<Review> findAllByBusinessId(int id);
}
