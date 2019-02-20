package org.parthinfotech.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.parthinfotech.validators.PasswordMatches;
import org.parthinfotech.validators.ValidEmail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PasswordMatches
public class UserDto {

	@NotNull
	@NotEmpty
	private String firstName;

	@NotNull
	@NotEmpty
	private String lastName;

	@NotNull
	@NotEmpty
	private String password;
	private String matchingPassword;

	@ValidEmail
	@NotNull
	@NotEmpty
	private String email;
}