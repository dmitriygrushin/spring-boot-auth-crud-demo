package com.dmitriyg.authMeetsCrud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dmitriyg.authMeetsCrud.model.Role;
import com.dmitriyg.authMeetsCrud.model.User;
import com.dmitriyg.authMeetsCrud.model.UserDetailsImpl;
import com.dmitriyg.authMeetsCrud.repository.RoleRepository;
import com.dmitriyg.authMeetsCrud.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	public void register(User user) {

		Optional<Role> role = roleRepository.findByName("USER");
		if (role.isPresent()) {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			user.addRole(role.get());
			user.setEnabled(true);

			userRepository.save(user);
		}

	}
	
	public User getCurrentAuthenticatedUser() {
		UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getUser();
	}

	public User getById(int id) {
		return userRepository.getReferenceById(id);
	}

}
