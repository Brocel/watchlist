package com.openclassroom.watchlist;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE) // <- Annotation applicable to classes
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BadMovieValidator.class)
public @interface BadMovie {

	String message() default "If a movie is less good than 6, then comment should contains at least 15 characters";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
