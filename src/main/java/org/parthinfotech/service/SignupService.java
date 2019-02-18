package org.parthinfotech.service;

import org.parthinfotech.exception.UserAlreadyExistException;
import org.parthinfotech.model.Signup;

public interface SignupService {

	public Signup createNewUser(Signup signup) throws UserAlreadyExistException;
}
