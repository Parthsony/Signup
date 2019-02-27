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

	public UserDto(@NotNull @NotEmpty String firstName, @NotNull @NotEmpty String lastName,
			@NotNull @NotEmpty String password, String matchingPassword, @NotNull @NotEmpty String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.matchingPassword = matchingPassword;
		this.email = email;
	}

	@NotNull(message = "First name is mandatory")
	@NotEmpty(message = "First name can't be empty")
	private String firstName;

	@NotNull(message = "Last name is mandatory")
	@NotEmpty(message = "Last name can't be empty")
	private String lastName;

	@NotNull(message = "Password is mandatory")
	@NotEmpty(message = "Password can't be empty")
	private String password;

	private String matchingPassword;

	@ValidEmail
	@NotNull
	@NotEmpty
	private String email;
}