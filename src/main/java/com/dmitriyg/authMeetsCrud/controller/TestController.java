package com.dmitriyg.authMeetsCrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/")
	public String test() {
		return "test";
	}

	@GetMapping("/user")
	public String userPage() {
		return "userPage";
	}

	@GetMapping("/moderator")
	public String moderatorPage() {
		return "moderatorPage";
	}

	@GetMapping("/admin")
	public String adminPage() {
		return "adminPage";
	}
}
