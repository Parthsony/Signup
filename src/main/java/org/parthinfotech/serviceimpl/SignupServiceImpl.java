package org.parthinfotech.serviceimpl;

import java.util.Date;
import java.util.UUID;

import javax.mail.MessagingException;

import org.parthinfotech.exception.UserAlreadyExistException;
import org.parthinfotech.mail.MailClient;
import org.parthinfotech.model.Signup;
import org.parthinfotech.repository.SignupRepository;
import org.parthinfotech.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SignupServiceImpl implements SignupService {

	@Autowired
	private SignupRepository repository;

	@Autowired
	private MailClient mailClient;

	@Autowired
	private Environment env;

	@Override
	public Signup createNewUser(Signup signup) throws MessagingException {

		validateNewSignupRequest(signup.getEmail());

		String requestId = UUID.randomUUID().toString();
		mailClient.prepareAndSend("Confirm your email", signup.getEmail(),
				"Please click on the below link to verify <br> <a href=" + prepareEmailMessage(requestId)
						+ "> click here </a>");

		signup.setRequestId(requestId);
		signup.setCreated(new Date());
		signup.setRequestId(requestId);
		return repository.save(signup);
	}

	public boolean activateUser(Signup user) {

		if (validateRequestTime(user.getCreated())) {
			user.setEmailVerified(true);
			repository.save(user);
			return true;
		}

		return false;
	}

	private boolean validateRequestTime(Date created) {

		long diffInMinutes = (new Date().getTime() - created.getTime()) / (60 * 1000);
		return diffInMinutes <= 15;
	}

	private void validateNewSignupRequest(String email) {

		if (null != repository.findByEmailIgnoreCase(email)) {
			throw new UserAlreadyExistException("User already exist with email id: " + email);
		}
	}

	private String prepareEmailMessage(String requestId) {
		return env.getProperty("app.environment") + "/signup/activate?request=" + requestId;
	}
}
