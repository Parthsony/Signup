package org.parthinfotech.validators.implementation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.parthinfotech.dto.SignupDto;
import org.parthinfotech.validators.PasswordMatches;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {
		// nothing to initialize
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		SignupDto user = (SignupDto) obj;
		return user.getPassword().equals(user.getPasswordMatches());
	}
}