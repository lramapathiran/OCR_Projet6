package com.lavanya.escalade.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lavanya.escalade.model.User;

/**
 * Constraint Validator used to implement the annotation PasswordMatches.
 * @author lavanya
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
	
	/**
    * Initializes the validator in preparation for isValid(Object, ConstraintValidatorContext) calls.
    * The constraint annotation for a given constraint declaration is passed.
    * This method is guaranteed to be called before any use of this instance for validation.
	*/
    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        
    }
    
    /**
     * Method to verify whether both passwords are identical and validate its entry in form.
     * @param obj, object to validate.
     * @param context, context in which the constraint is evaluated.
     * @return boolean, true if both passwords are identical, false otherwise.
     */
    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final User user = (User) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }

}
