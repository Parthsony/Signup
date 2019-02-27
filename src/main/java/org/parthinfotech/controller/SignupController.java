package org.parthinfotech.controller;

import java.net.URI;

import javax.mail.MessagingException;
import javax.validation.Valid;

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

/**
 * Controller of sign-up process for new user
 * 
 * @author Parth Soni
 *
 */
@RestController
@RequestMapping("/signup")
public class SignupController {

	@Autowired
	private SignupServiceImpl signupServiceImpl;

	@Autowired
	private SignupRepository repository;

	/**
	 * Since, email should be unique of all the users Check whether requested email
	 * is available or not.
	 * 
	 * @param email Email address
	 * @return true if email id available, false otherwise
	 */
	@GetMapping("/available")
	@ResponseBody
	public boolean isEmailAvailable(@RequestParam String email) {
		return repository.countByEmailIgnoreCase(email) == 0;
	}

	/**
	 * Sign-up new user
	 * <p>
	 * In addition, this method converts sign-up DTO to an entity object, then
	 * validate user by checking email id (if it is not registered by requested
	 * email), then if new email found then send an email to do further confirmation
	 * 
	 * @param newUserDto Data transfer object for registration
	 * @return newly created url
	 * @throws MessagingException
	 */
	@PostMapping
	public ResponseEntity<Void> doSignup(@Valid @RequestBody SignupDto newUserDto) throws MessagingException {

		Signup requestEntity = (Signup) DtoUtility.dtoToEntity(newUserDto, Signup.class);

		Signup newUserModel = signupServiceImpl.createNewUser(requestEntity);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newUserModel.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	/**
	 * Activate new user if user account not already been activated and link is not
	 * expired by request id
	 * 
	 * @param request Request id
	 * @return true if activated, false otherwise
	 */
	@GetMapping("/activate")
	public boolean activateAccount(@RequestParam("request") String request) {

		Signup user = repository.findByRequestId(request);

		if (null != user && !user.isEmailVerified()) {
			return signupServiceImpl.activateUser(user);
		}
		return false;
	}

}
