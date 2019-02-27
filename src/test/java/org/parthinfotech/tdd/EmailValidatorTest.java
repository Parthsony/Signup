package org.parthinfotech.tdd;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.junit.jupiter.api.Test;
import org.parthinfotech.dto.UserDto;

public class EmailValidatorTest {
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+ (.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

	@Test
	void testThis() {
		
		testUserDto(new UserDto(null, "Cho", "Yes", "No", null));
	}
	
	void testUserDto(@Valid UserDto dto) {
		
		System.out.println(dto.getEmail());
	}

	public boolean isValid(String email) {
		return (validateEmail(email));
	}

	private boolean validateEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
