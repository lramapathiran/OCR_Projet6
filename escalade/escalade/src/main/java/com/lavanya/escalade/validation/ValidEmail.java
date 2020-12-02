package com.lavanya.escalade.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validation annotation
 * Validates that the email adress entered in user creation form has a valid pattern. 
 * @author lavanya
 */
@Target({TYPE, FIELD, ANNOTATION_TYPE}) 
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {
	
	String message() default "Veuillez rentrer une adresse email valide!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
