package com.openclassroom.watchlist.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {

	private final Logger logger = LoggerFactory.getLogger(CustomErrorController.class);
	
	@Override
	public String getErrorPath() {
		
		return "/error";
	}

	@GetMapping("/error")
	public ModelAndView handleError(HttpServletResponse response) {
		
		int code = response.getStatus();
		System.out.println("Error with code " + code + " happened !");
		
		logger.error("Error with code {} happened !", code);
		
		if (code == 404) {
			return new ModelAndView("error_404");
		}
		
		return new ModelAndView("error");
	}
}
