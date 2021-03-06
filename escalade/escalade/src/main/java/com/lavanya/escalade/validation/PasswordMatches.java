package com.lavanya.escalade.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * Validation annotation
 * Validates that the 2 passwords entered in user creation form are identical. 
 * @author lavanya
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {

    String message() default "Les deux mots de passe ne sont pas identiques";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
