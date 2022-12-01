package com.dmitriyg.authMeetsCrud.controller;

import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dmitriyg.authMeetsCrud.model.Role;
import com.dmitriyg.authMeetsCrud.model.User;
import com.dmitriyg.authMeetsCrud.repository.RoleRepository;
import com.dmitriyg.authMeetsCrud.repository.UserRepository;

@Controller
public class AuthenticationController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;

	@GetMapping("/register")
	public String registrationForm(Model model) {
		model.addAttribute("user", new User());
		return "authentication/register";
	}
	
	@PostMapping("/register")
	public String register(User user) throws Exception {

		Optional<Role> role = roleRepository.findByName("USER");
		if (role.isEmpty()) throw new NotFoundException();

		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.addRole(role.get());
		user.setEnabled(true);

		userRepository.save(user);
		return "redirect:/";

	}

}
