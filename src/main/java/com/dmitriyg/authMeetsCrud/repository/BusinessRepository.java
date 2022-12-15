package com.dmitriyg.authMeetsCrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmitriyg.authMeetsCrud.model.Business;

public interface BusinessRepository extends JpaRepository<Business, Integer> {
	int countByUserIdAndId(int userId, long id);
}
