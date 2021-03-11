package com.openclassroom.watchlist.service;

import com.openclassroom.watchlist.repository.WatchlistRepository;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.openclassroom.watchlist.domain.WatchlistItem;
import com.openclassroom.watchlist.exception.DuplicateTitleException;
import com.openclassroom.watchlist.exception.WatchlistFullException;

public class WatchlistService {

	WatchlistRepository watchlistRepository = new WatchlistRepository();
	
	public List<WatchlistItem> getWatchlistItems() {
		
		return watchlistRepository.getList();
	}
	
	public int getWatchlistItemsSize() {
		
		return watchlistRepository.getList().size();
	}
	
	public WatchlistItem findWatchlistItemById(Integer id) {
		
		return watchlistRepository.findById(id);
	}
	
	public void addOrUpdateWatchlistItem(WatchlistItem watchlistItem) throws WatchlistFullException, DuplicateTitleException {
		
		WatchlistItem existingItem = findWatchlistItemById(watchlistItem.getId());
		
		if (existingItem ==  null) {
			
			if (getWatchlistItemsSize()>= watchlistRepository.maximumMovies) {
				
				throw new WatchlistFullException();
			}
			
			if (watchlistRepository.findByTitle(watchlistItem.getTitle()) != null) {
				
				throw new DuplicateTitleException();
			}
			watchlistRepository.addItem(watchlistItem);
		} else {
			existingItem.setComment(watchlistItem.getComment());
			existingItem.setPriority(watchlistItem.getPriority());
			existingItem.setRating(watchlistItem.getRating());
			existingItem.setTitle(watchlistItem.getTitle());
		}
	}
}
