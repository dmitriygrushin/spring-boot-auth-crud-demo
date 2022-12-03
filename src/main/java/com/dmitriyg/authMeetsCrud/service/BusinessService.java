package com.dmitriyg.authMeetsCrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmitriyg.authMeetsCrud.model.Business;
import com.dmitriyg.authMeetsCrud.repository.BusinessRepository;

@Service
public class BusinessService {
	@Autowired
	BusinessRepository businessRepository;

	public void save(Business business) {
		businessRepository.save(business);
	}

}
