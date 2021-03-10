package com.openclassroom.watchlist;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {

	@Override
	public String getErrorPath() {
		
		return "/error";
	}

	@GetMapping("/error")
	public ModelAndView handleError(HttpServletResponse response) {
		
		int code = response.getStatus();
		System.out.println("Error with code " + code + " happened !");
		
		return new ModelAndView("error");
	}
}
