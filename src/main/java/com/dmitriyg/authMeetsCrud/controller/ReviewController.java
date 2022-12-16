package com.dmitriyg.authMeetsCrud.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dmitriyg.authMeetsCrud.model.Review;
import com.dmitriyg.authMeetsCrud.model.User;
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

	private static final String STOP_OWNER_FROM_REVIEWING = 
			"@userServiceImpl.getCurrentAuthenticatedUser().getId() != "
			+ "@businessServiceImpl.findById(#businessId).get().getUser().getId()";

	private static final String CHECK_IF_USER_OWNS_REVIEW = 
			"@userServiceImpl.getCurrentAuthenticatedUser().getId() == "
			+ "@reviewServiceImpl.findById(#id).get().getUser().getId()";
	
	private static final String STOP_MULTIPLE_BUSINESS_REVIEWS_BY_USER = 
			"@reviewServiceImpl.userReviewedBusiness(@userServiceImpl.getCurrentAuthenticatedUser().getId(), #businessId)";

	private static final String POST_CHECK_IF_USER_OWNS_REVIEW = 
			"@reviewServiceImpl.checkIfUserOwnsReview("
			+ "@userServiceImpl.getCurrentAuthenticatedUser().getId(), #review)";

	@GetMapping("/my-list") 
	public String myList(Model model) {
		int userId = userService.getCurrentAuthenticatedUser().getId();
		User user = userService.getById(userId);
		List<Review> reviews = user.getReviews();
		model.addAttribute("reviews", reviews);

		return "review/my-list";
	}
	
	@GetMapping("/create") 
	@PreAuthorize(STOP_OWNER_FROM_REVIEWING + " && " + STOP_MULTIPLE_BUSINESS_REVIEWS_BY_USER)
	public String createForm(@RequestParam("businessId") int businessId, Model model) {
		Review review = new Review();
		review.setBusiness(businessService.findById(businessId).get());
		review.setDate(LocalDate.now());
		model.addAttribute("review", review);
		
		return "review/save";
	}


	@GetMapping("/update") 
	@PreAuthorize(CHECK_IF_USER_OWNS_REVIEW)
	public String updateForm(@RequestParam("reviewId") int id, Model model) {
		Optional<Review> review = reviewService.findById(id);
		model.addAttribute("review", review.get());
		return "review/save";
	}
	
	@GetMapping("/delete")
	@PreAuthorize(CHECK_IF_USER_OWNS_REVIEW)
	public String delete(@RequestParam("reviewId") int id) {
		reviewService.deleteById(id);
		return "redirect:/review/my-list";
	}
	
	@PostMapping("/save")
	@PreAuthorize(POST_CHECK_IF_USER_OWNS_REVIEW)
	public String save(@ModelAttribute("review") Review review) {
		if (review.getUser() == null) review.setUser(userService.getCurrentAuthenticatedUser());
		reviewService.save(review);
		return "redirect:/business/view?businessId=" + review.getBusiness().getId();
	}
	
	
}
