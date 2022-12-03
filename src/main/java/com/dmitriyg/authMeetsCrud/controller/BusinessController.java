package com.dmitriyg.authMeetsCrud.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dmitriyg.authMeetsCrud.model.Business;
import com.dmitriyg.authMeetsCrud.model.UserDetailsImpl;
import com.dmitriyg.authMeetsCrud.service.BusinessService;
import com.dmitriyg.authMeetsCrud.service.UserService;

@Controller
public class BusinessController {
	
	@Autowired
	BusinessService businessService;
	
	@Autowired
	UserService userService;

	@GetMapping("/business/create")
	public String create(Model model) {
		Business business = new Business();
		business.setDate(LocalDate.now());
		model.addAttribute("business", business);


		return "business/create";
	}
	
	@PostMapping("/business")
	public String create(@ModelAttribute("business") Business business) {
		UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		business.setUser(user.getUser());
		businessService.save(business);
		System.out.println(business);	
		return "redirect:/";
	}


	
	

}
