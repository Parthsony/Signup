package org.parthinfotech.controller;

import java.net.URI;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.parthinfotech.dto.SignupDto;
import org.parthinfotech.dto.UserDto;
import org.parthinfotech.exception.UserAlreadyExistException;
import org.parthinfotech.model.Signup;
import org.parthinfotech.model.User;
import org.parthinfotech.repository.SignupRepository;
import org.parthinfotech.serviceimpl.SignupServiceImpl;
import org.parthinfotech.utility.DtoUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
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

	@PostMapping("/newUser")
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto newUserDto, BindingResult result,
			WebRequest request, Errors errors) {

		if (!result.hasErrors()) {
		}
		return null;
	}

	@PostMapping
	public ResponseEntity<Void> doSignup(@RequestBody SignupDto newUserDto) throws MessagingException {

		Signup requestEntity = (Signup) DtoUtility.dtoToEntity(newUserDto, Signup.class);

		Signup newUserModel = signupServiceImpl.createNewUser(requestEntity);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
				.buildAndExpand(newUserModel.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PatchMapping("/activate")
	public boolean activateAccount() {
		return false;
	}
	
	private User createUserAccount(UserDto accountDto, BindingResult result) {
	    
		User registered = null;
	    try {
	        registered = signupServiceImpl.registerNewUserAccount(accountDto);
	    } catch (UserAlreadyExistException e) {
	        return null;
	    }    
	    return registered;
	}
}
