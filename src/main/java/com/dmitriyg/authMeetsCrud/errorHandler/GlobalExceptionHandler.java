package com.dmitriyg.authMeetsCrud.errorHandler;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

//@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger;
	
	public GlobalExceptionHandler() {
		logger = LoggerFactory.getLogger(getClass());
	}

	@ExceptionHandler({NoSuchElementException.class, EmptyResultDataAccessException.class})
	public ModelAndView noSuchElement(HttpServletRequest req, Exception ex) {
		logger.error("Request: " + req.getRequestURL() + " raised " + ex);

		ModelAndView model = new ModelAndView();
		//model.addObject("exception", ex);
		model.addObject("message", "No such element exists");
		model.addObject("url", req.getRequestURL());
		model.setViewName("error");
		return model;
	}
	
	  @ExceptionHandler(Exception.class)
	  public ModelAndView defaultErrorHander(HttpServletRequest req, Exception ex) {
	    logger.error("Request: " + req.getRequestURL() + " raised " + ex);


	    ModelAndView mav = new ModelAndView();
	    mav.addObject("message", "Something went wrong...");
	    mav.addObject("url", req.getRequestURL());
	    mav.setViewName("error");
	    return mav;
	  }

	
}
