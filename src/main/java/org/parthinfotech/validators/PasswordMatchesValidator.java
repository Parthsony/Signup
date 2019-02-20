package org.parthinfotech.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.parthinfotech.dto.UserDto;

public class PasswordMatchesValidator 
  implements ConstraintValidator<PasswordMatches, Object> { 
     
    @Override
    public void initialize(PasswordMatches constraintAnnotation) { 
    	// nothing to initialize
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){   
        UserDto user = (UserDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());    
    }     
}