package org.parthinfotech.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDto {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
