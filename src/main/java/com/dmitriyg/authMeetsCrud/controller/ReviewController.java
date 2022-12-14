package com.dmitriyg.authMeetsCrud.controller;

import java.time.LocalDate;
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
import com.dmitriyg.authMeetsCrud.model.Review;
import com.dmitriyg.authMeetsCrud.service.BusinessService;
import com.dmitriyg.authMeetsCrud.service.ReviewService;
import com.dmitriyg.authMeetsCrud.service.UserService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReviewService reviewService;

	@Autowired
	private BusinessService businessService;

	@GetMapping("/test")
	public String test(@RequestParam("reviewId") Integer id) {
		List<Review> reviews = reviewService.findAllByBusinessId(id);
		
		for (Review review : reviews) {
			System.out.println(review.getDescription());
		}

		return "/index";
	}
	
	@GetMapping("/create") 
	public String createReview(@RequestParam("businessId") int businessId, Model model) {
		Review review = new Review();
		review.setBusiness(businessService.findById(businessId).get());
		review.setDate(LocalDate.now());
		model.addAttribute("review", review);
		
		return "review/save";
	}
	
	
	@PostMapping("/save")
	public String save(@ModelAttribute("review") Review review) {
		if (review.getUser() == null) review.setUser(userService.getCurrentAuthenticatedUser());
		
		reviewService.save(review);
		
		return "redirect:/business/view?businessId=" + review.getBusiness().getId();
	}
	
	
}
