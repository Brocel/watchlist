package com.openclassroom.watchlist.repository;

import java.util.ArrayList;
import java.util.List;

import com.openclassroom.watchlist.domain.WatchlistItem;

public class WatchlistRepository {

	private List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();
	private static int index = 1;
	public static int maximumMovies = 3;
	
	public List<WatchlistItem> getList() {
		
		return watchlistItems;
	}
	
	public void addItem(WatchlistItem watchlistItem) {
		
		watchlistItem.setId(index++);
		watchlistItems.add(watchlistItem);
	}
	
	public WatchlistItem findById(Integer id) {
		
		for (WatchlistItem watchlistItem : watchlistItems) {
			if (watchlistItem.getId().equals(id)) {
				return watchlistItem;
			}
		}
		return null;
	}
	
	public WatchlistItem findByTitle(String title) {
		
		for (WatchlistItem watchlistItem : watchlistItems) {
			if (watchlistItem.getTitle().equals(title)) {
				return watchlistItem;
			}
		}
		return null;
	}
	
	
}