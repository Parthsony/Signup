package org.parthinfotech.serviceimpl;

import org.parthinfotech.exception.UserAlreadyExistException;
import org.parthinfotech.model.Signup;
import org.parthinfotech.repository.SignupRepository;
import org.parthinfotech.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignupServiceImpl implements SignupService {

	@Autowired
	private SignupRepository repository;

	@Override
	public Signup createNewUser(Signup signup) {

		if (null != repository.findByEmailIgnoreCase(signup.getEmail())) {
			throw new UserAlreadyExistException("User already exist with email id: " + signup.getEmail());
		}

		return repository.save(signup);
	}

}
