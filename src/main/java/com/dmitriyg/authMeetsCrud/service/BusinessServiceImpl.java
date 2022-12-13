package com.dmitriyg.authMeetsCrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmitriyg.authMeetsCrud.model.Business;
import com.dmitriyg.authMeetsCrud.repository.BusinessRepository;

@Service
public class BusinessServiceImpl implements BusinessService {

	@Autowired
	private BusinessRepository businessRepository;

	@Override
	public void save(Business business) {
		businessRepository.save(business);
	}

	@Override
	public Optional<Business> findById(int id) {
		return businessRepository.findById(id);
	}
	
	@Override 
	public List<Business> findAll() {
		return businessRepository.findAll();
	}
	@Override
	public void deleteById(int id) {
		businessRepository.deleteById(id);
	}

}
