package org.parthinfotech.serviceimpl;

import org.parthinfotech.SignupRepository;
import org.parthinfotech.exception.UserAlreadyExistException;
import org.parthinfotech.model.Signup;
import org.parthinfotech.service.SignupService;

public class SignupServiceImpl implements SignupService {

	private SignupRepository repository;

	@Override
	public Signup createNewUser(Signup signup) {

		if (repository.findByEmailContainingIgnoreCase(signup.getEmail())) {
			throw new UserAlreadyExistException("User Already exist");
		}
		return null;
	}

}
