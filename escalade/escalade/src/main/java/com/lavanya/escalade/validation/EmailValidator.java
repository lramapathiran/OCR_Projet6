package com.lavanya.escalade.validation;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Constraint Validator used to implement the annotation ValidEmail.
 * @author lavanya
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
	
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);
    
    /**
     * Method to verify whether the username is valid using the method validateEmail().
     * @param username, username entered in the user creation form to check for validation.
     * @param context, context in which the constraint is evaluated.
     * @return boolean, true if the username is valid, false otherwise.
     */
    @Override
    public boolean isValid(final String username, final ConstraintValidatorContext context) {
        return (validateEmail(username));
    }
    
    /**
     * Method to verify whether an email has the right pattern.
     * @param email, email entered in the user creation form to check for validation.
     * @return boolean, true if the email pattern is respected, false otherwise.
     */
    private boolean validateEmail(final String email) {
        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }
}
