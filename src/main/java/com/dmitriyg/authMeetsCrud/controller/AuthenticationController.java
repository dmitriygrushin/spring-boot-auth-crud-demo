package com.dmitriyg.authMeetsCrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dmitriyg.authMeetsCrud.model.User;
import com.dmitriyg.authMeetsCrud.service.UserService;

@Controller
public class AuthenticationController {
	
	@Autowired 
	UserService userService;

	@GetMapping("/register")
	public String registrationForm(Model model) {
		model.addAttribute("user", new User());

		return "authentication/register";
	}

	@GetMapping("/login")
	public String loginForm() {
		return "authentication/login";
	}
	
	@PostMapping("/register")
	public String register(User user) {
		userService.register(user);

		return "redirect:/";
	}

}
