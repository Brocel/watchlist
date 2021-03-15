package com.openclassroom.watchlist.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.openclassroom.watchlist.service.MovieRatingService;

@Component // <- create a spring bean
public class HealthCheck implements HealthIndicator {

	private MovieRatingService movieRatingService;
	
	@Autowired
	public HealthCheck(MovieRatingService movieRatingService) {
		super();
		this.movieRatingService = movieRatingService;
	}



	@Override
	public Health health() {
		
		if (movieRatingService.getMovieRating("Up") ==  null) {
			return Health.down().withDetail("Cause", "OMDb API is not available").build();
		}
		
		return Health.up().build();
	}

}
