package org.parthinfotech.serviceimpl;

import java.util.Date;

import javax.mail.MessagingException;

import org.parthinfotech.exception.UserAlreadyExistException;
import org.parthinfotech.mail.MailClient;
import org.parthinfotech.model.Signup;
import org.parthinfotech.repository.SignupRepository;
import org.parthinfotech.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignupServiceImpl implements SignupService {

	@Autowired
	private SignupRepository repository;

	@Autowired
	private MailClient mailClient;

	@Override
	public Signup createNewUser(Signup signup) throws MessagingException {

		validateNewSignupRequest(signup.getEmail());
		mailClient.prepareAndSend("Confirm your email", signup.getEmail(), "Please click on the below link to verify");
		signup.setCreated(new Date());
		return repository.save(signup);
	}

	private void validateNewSignupRequest(String email) {

		if (null != repository.findByEmailIgnoreCase(email)) {
			throw new UserAlreadyExistException("User already exist with email id: " + email);
		}
	}

}
