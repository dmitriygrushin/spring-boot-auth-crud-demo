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

	@Override
	public boolean checkIfUserOwnsBusiness(int userId, Business business) {
		// new business so, no need to check
		if (business.getId() == null) return true;
		
		// if there is 1 count of business by the businessId that has a userId then the userId owns this business
		return businessRepository.countByUserIdAndId(userId, business.getId()) == 1;
	}

}
