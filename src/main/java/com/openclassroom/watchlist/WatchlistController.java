package com.openclassroom.watchlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
 	

@Controller
public class WatchlistController {

	private List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();
	private static int index = 1;
	
	// Adding a web form
	@GetMapping("/watchlistItemForm")
	public ModelAndView showWatchlistItemForm() {
		
		String viewName = "watchlistItemForm";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("watchlistItem", new WatchlistItem());
		
		return new ModelAndView(viewName, model);
	}

	@GetMapping("/watchlist")
	public ModelAndView getWatchlist() {
		
		watchlistItems.clear();
		
		watchlistItems.add(new WatchlistItem(index++, "Lion King", "8,5", "High", "Hakuna matata"));
		watchlistItems.add(new WatchlistItem(index++, "Betlegeuse", "9", "High", "Say my name"));
		watchlistItems.add(new WatchlistItem(index++, "Film3", "4", "Low", "!?!"));
		watchlistItems.add(new WatchlistItem(index++, "Film4", "6", "Medium", "?!?"));
		
		String viewName = "watchlist";
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		model.put("watchlistItems", watchlistItems);
		model.put("numberOfMovies", watchlistItems.size());
		
		return new ModelAndView(viewName, model);
		
	}
}
