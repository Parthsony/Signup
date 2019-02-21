package org.parthinfotech.tdd;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

public class EmailValidatorTest {
	
	enum COLOR{

		RED, BLUE, GREEN;
		
		void hello(){
			
		}
	}
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+ (.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

	@Test
	void testThis() {
		System.out.println(isValid("Parthsony1993@live.com"));
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
