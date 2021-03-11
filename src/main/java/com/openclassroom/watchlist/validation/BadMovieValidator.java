package com.openclassroom.watchlist.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.openclassroom.watchlist.domain.WatchlistItem;

public class BadMovieValidator implements ConstraintValidator<BadMovie, WatchlistItem> {

	@Override
	public boolean isValid(WatchlistItem value, ConstraintValidatorContext context) {
		
		return !(Double.valueOf(value.getRating()) <= 6 
				&& value.getComment().trim().length() < 15);
	}

}
