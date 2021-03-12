package com.openclassroom.watchlist.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.openclassroom.watchlist.domain.WatchlistItem;
import com.openclassroom.watchlist.exception.DuplicateTitleException;
import com.openclassroom.watchlist.exception.WatchlistFullException;
import com.openclassroom.watchlist.service.WatchlistService;
 	

@Controller
public class WatchlistController {

	private WatchlistService watchlistService;
	
	private final Logger logger = LoggerFactory.getLogger(WatchlistController.class);
	
	@Autowired	
	public WatchlistController(WatchlistService watchlistService) {
		super();
		this.watchlistService = watchlistService;
	}

	// Adding a web form
	@GetMapping("/watchlistItemForm")
	public ModelAndView showWatchlistItemForm(@RequestParam(required = false) Integer id) {
		
		logger.info("HTTP GET Request received at /watchlistItemForm URL");
		
		String viewName = "watchlistItemForm";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		WatchlistItem watchlistItem = watchlistService.findWatchlistItemById(id);
		
		if (watchlistItem == null) {
			model.put("watchlistItem", new WatchlistItem());
		} else {
			model.put("watchlistItem", watchlistItem);
		}
		
		return new ModelAndView(viewName, model);
	}

	@PostMapping("/watchlistItemForm")
	public ModelAndView submitWatchlistItemForm(@Valid WatchlistItem watchlistItem, BindingResult bindingResult) {

		logger.info("HTTP POST Request received at /watchlistItemForm URL");
		
		if (bindingResult.hasErrors()) {
			return new ModelAndView("watchlistItemForm");
		}
		
		
		try {
			watchlistService.addOrUpdateWatchlistItem(watchlistItem);
		} catch (WatchlistFullException e) {
			bindingResult.rejectValue(null, "", e.errorMessage);
			return new ModelAndView("watchlistItemForm");
		} catch (DuplicateTitleException e) {
			bindingResult.rejectValue("title", "", e.errorMessage);
			return new ModelAndView("watchlistItemForm");
		}
		
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/watchlist");
		
		return new ModelAndView(redirect);
	}

	@GetMapping("/watchlist")
	public ModelAndView getWatchlist() {
		
		logger.info("HTTP GET Request received at /watchlist URL");
		
		String viewName = "watchlist";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("watchlistItems", watchlistService.getWatchlistItems());
		model.put("numberOfMovies", watchlistService.getWatchlistItemsSize());
		
		return new ModelAndView(viewName, model);	
	}
	
}
