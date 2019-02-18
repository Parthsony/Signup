package org.parthinfotech.controller;

import org.parthinfotech.dto.SignupDto;
import org.parthinfotech.model.Signup;
import org.parthinfotech.serviceimpl.SignupServiceImpl;
import org.parthinfotech.utility.DtoUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupController {

	@Autowired
	SignupServiceImpl signupServiceImpl;

	@PostMapping
	public ResponseEntity<SignupDto> saveNewUser(@RequestBody SignupDto newUser){

		Signup signup = (Signup) DtoUtility.convertDtoToModel(newUser, Signup.class);

		signupServiceImpl.createNewUser(signup);

		return null;
	}
}
