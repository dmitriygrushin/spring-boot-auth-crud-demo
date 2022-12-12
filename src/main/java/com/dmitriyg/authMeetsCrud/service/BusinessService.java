package com.dmitriyg.authMeetsCrud.service;

import java.util.Optional;

import com.dmitriyg.authMeetsCrud.model.Business;

public interface BusinessService {

	void save(Business business);

	Optional<Business> findById(int id);

	void deleteById(int id);

}