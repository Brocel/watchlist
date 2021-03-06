package com.openclassroom.watchlist.service;

import com.openclassroom.watchlist.repository.WatchlistRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassroom.watchlist.domain.WatchlistItem;
import com.openclassroom.watchlist.exception.DuplicateTitleException;
import com.openclassroom.watchlist.exception.WatchlistFullException;

@Service
public class WatchlistService {

	WatchlistRepository watchlistRepository;
	MovieRatingService movieRatingService;
	
	@Autowired
	public WatchlistService(WatchlistRepository watchlistRepository, MovieRatingService movieRatingService) {
		super();
		this.watchlistRepository = watchlistRepository;
		this.movieRatingService = movieRatingService;
	}

	public List<WatchlistItem> getWatchlistItems(){
		
		List<WatchlistItem> watchlistItems = watchlistRepository.getList();
		
		for (WatchlistItem watchlistItem : watchlistItems) {
		    
			String rating = movieRatingService.getMovieRating(watchlistItem.getTitle());
			
			if (rating != null) {
				watchlistItem.setRating(rating);
			}
		}
		return watchlistItems;
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
			
			if (getWatchlistItemsSize() >= watchlistRepository.maximumMovies) {
				
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
