package com.openclassroom.watchlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
 	

@Controller
public class WatchlistController {

	private List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();
	private static int index = 1;
	
	// Adding a web form
	@GetMapping("/watchlistItemForm")
	public ModelAndView showWatchlistItemForm(@RequestParam(required = false) Integer id) {
		
		String viewName = "watchlistItemForm";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		WatchlistItem watchlistItem = findWatchlistItemById(id);
		
		if (watchlistItem == null) {
			model.put("watchlistItem", new WatchlistItem());
		} else {
			model.put("watchlistItem", watchlistItem);
		}
		
		return new ModelAndView(viewName, model);
	}
	
	private WatchlistItem findWatchlistItemById(Integer id) {
		
		for (WatchlistItem watchlistItem : watchlistItems) {
			
			if (watchlistItem.getId().equals(id)) {
				return watchlistItem;
			}
		}
		
		return null;
	}

	@PostMapping("/watchlistItemForm")
	public ModelAndView submitWatchlistItemForm(WatchlistItem watchlistItem) {
		
		watchlistItem.setId(index++);
		watchlistItems.add(watchlistItem);
		
		RedirectView redirect = new RedirectView();
		redirect.setUrl("/watchlist");
		
		return new ModelAndView(redirect);
		
	}

	@GetMapping("/watchlist")
	public ModelAndView getWatchlist() {
		
		String viewName = "watchlist";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("watchlistItems", watchlistItems);
		model.put("numberOfMovies", watchlistItems.size());
		
		return new ModelAndView(viewName, model);
		
	}
}
