package com.openclassroom.watchlist.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE) // <- Annotation applicable to classes
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GoodMovieValidator.class)
public @interface GoodMovie {

	String message() default "If a movie is as good as 8, then priority should be at least M";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}