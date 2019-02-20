package org.parthinfotech.service;

import javax.mail.MessagingException;

import org.parthinfotech.model.Signup;

public interface SignupService {

	public Signup createNewUser(Signup signup)  throws MessagingException;
}
