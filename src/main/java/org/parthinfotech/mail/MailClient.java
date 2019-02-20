package org.parthinfotech.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailClient {

	private JavaMailSender javaMailSender;

	@Autowired
	public MailClient(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void prepareAndSend(String subject, String sendTo, String text) throws MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setSubject(subject);
		helper.setTo(sendTo);
		helper.setText(text);

		javaMailSender.send(message);
	}

}
