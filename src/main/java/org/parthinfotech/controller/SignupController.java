package org.parthinfotech.controller;

import java.net.URI;

import javax.mail.MessagingException;

import org.parthinfotech.dto.SignupDto;
import org.parthinfotech.model.Signup;
import org.parthinfotech.repository.SignupRepository;
import org.parthinfotech.serviceimpl.SignupServiceImpl;
import org.parthinfotech.utility.DtoUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/signup")
public class SignupController {

	@Autowired
	private SignupServiceImpl signupServiceImpl;

	@Autowired
	private SignupRepository repository;

	@GetMapping("/available")
	@ResponseBody
	public boolean isEmailAvailable(@RequestParam String email) {
		return repository.countByEmailIgnoreCase(email) == 0;
	}

	@PostMapping
	public ResponseEntity<Void> doSignup(@RequestBody SignupDto newUserDto) throws MessagingException {

		Signup requestEntity = (Signup) DtoUtility.dtoToEntity(newUserDto, Signup.class);

		Signup newUserModel = signupServiceImpl.createNewUser(requestEntity);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
				.buildAndExpand(newUserModel.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/activate")
	public boolean activateAccount(@RequestParam("request") String request) {

		Signup user = repository.findByRequestId(request);

		if (null != user && !user.isEmailVerified()) {
			return signupServiceImpl.activateUser(user);
		}
		return false;
	}

}
