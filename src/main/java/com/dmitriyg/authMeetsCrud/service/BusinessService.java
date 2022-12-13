package com.dmitriyg.authMeetsCrud.service;

import java.util.List;
import java.util.Optional;

import com.dmitriyg.authMeetsCrud.model.Business;

public interface BusinessService {

	void save(Business business);

	void deleteById(int id);

	Optional<Business> findById(int id);

	List<Business> findAll();

}