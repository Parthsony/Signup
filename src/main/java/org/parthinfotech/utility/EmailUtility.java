package org.parthinfotech.utility;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailUtility {

	private static JavaMailSender sender;

	@Autowired
	synchronized void init(JavaMailSender sender) {
		EmailUtility.sender = sender;
	}

	public static void sendEmail(String subject, String[] sendTo, String text) throws MessagingException {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setSubject(subject);
		helper.setTo(sendTo);
		helper.setText(text);

		sender.send(message);
	}
	
	public static void sendEmail(String subject, String sendTo, String text) throws MessagingException {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setSubject(subject);
		helper.setTo(sendTo);
		helper.setText(text);

		sender.send(message);
	}
}
