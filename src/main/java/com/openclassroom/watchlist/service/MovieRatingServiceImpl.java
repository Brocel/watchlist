package com.openclassroom.watchlist.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name="app.environment", havingValue="prod")
public class MovieRatingServiceImpl implements MovieRatingService {

	@Override
	public String getMovieRating(String title) {
		// TODO Auto-generated method stub : add the api movie rating
		return null;
	}

}
