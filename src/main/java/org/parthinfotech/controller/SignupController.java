package org.parthinfotech.controller;

import java.net.URI;

import org.modelmapper.ModelMapper;
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

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/available")
	@ResponseBody
	public boolean isEmailAvailable(@RequestParam String email) {
		return null == repository.findByEmailIgnoreCase(email);
	}

	@PostMapping
	public ResponseEntity<Void> saveNewUser(@RequestBody SignupDto newUserDto) {

		Signup convertDtoToModel = (Signup) DtoUtility.convertDtoToModel(newUserDto, Signup.class, mapper);
		Signup newUserModel = signupServiceImpl.createNewUser(convertDtoToModel);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(newUserModel.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
}
