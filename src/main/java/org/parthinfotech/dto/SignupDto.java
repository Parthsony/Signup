package org.parthinfotech.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.parthinfotech.validators.PasswordMatches;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PasswordMatches
public class SignupDto {

	private Long id;

	@NotNull(message = "First name is mandatory")
	@NotEmpty(message = "First name can't be empty")
	private String firstName;

	@NotNull(message = "Last name is mandatory")
	@NotEmpty(message = "Last name can't be empty")
	private String lastName;

	@Email
	private String email;

	@NotNull(message = "Password is mandatory")
	@NotEmpty(message = "Password can't be empty")
	private String password;

	private String passwordMatches;

	private boolean emailVerified;
}