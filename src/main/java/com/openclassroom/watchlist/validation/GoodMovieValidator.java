package com.openclassroom.watchlist.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.openclassroom.watchlist.domain.WatchlistItem;

public class GoodMovieValidator implements ConstraintValidator<GoodMovie, WatchlistItem> {

	@Override
	public boolean isValid(WatchlistItem value, ConstraintValidatorContext context) {
		
		return !(Double.valueOf(value.getRating()) >= 8 
				&& "L".equals(value.getPriority().trim().toUpperCase()));
	}

}
