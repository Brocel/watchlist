package com.openclassroom.watchlist.exception;

import com.openclassroom.watchlist.repository.WatchlistRepository;

public class WatchlistFullException extends Exception {

	private int maximumMovies = WatchlistRepository.maximumMovies;
	
	public String errorMessage = "Your watchlist is full (contains already " + maximumMovies + " items)";
}
