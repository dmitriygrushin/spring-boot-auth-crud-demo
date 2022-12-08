package com.dmitriyg.authMeetsCrud.controller;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dmitriyg.authMeetsCrud.model.Business;
import com.dmitriyg.authMeetsCrud.model.User;
import com.dmitriyg.authMeetsCrud.service.BusinessService;
import com.dmitriyg.authMeetsCrud.service.UserService;

@Controller
@RequestMapping("/business")
public class BusinessController {
	
	@Autowired
	BusinessService businessService;
	
	@Autowired
	UserService userService;

	@GetMapping("/list")
	public String list(Model model) {
		int userId = userService.getCurrentAuthenticatedUser().getId();
		User user = userService.getById(userId);
		List<Business> businesses = user.getBusinesses();
		model.addAttribute("businesses", businesses);

		return "business/list";
	}

	@GetMapping("/create")
	public String createForm(Model model) {
		Business business = new Business();
		business.setDate(LocalDate.now());
		model.addAttribute("business", business);

		return "business/save";
	}

	@GetMapping("/update")
	public String updateForm(@RequestParam("businessId") int id, Model model) {
		Optional<Business> business = businessService.findById(id);

		model.addAttribute("business", business.get());

		return "business/save";
			
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("business") Business business) {
		business.setUser(userService.getCurrentAuthenticatedUser());
		businessService.save(business);

		return "redirect:/business/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("businessId") int id, Model model) {
		businessService.deleteById(id);

		return "redirect:/business/list";
			
	}

}
