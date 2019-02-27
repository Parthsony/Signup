package org.parthinfotech.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.parthinfotech.model.Signup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "forgot_password_requests")
public class ForgotPasswordRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id = UUID.randomUUID().toString();

	private Date created = new Date();

	@ManyToOne
	private Signup user;

	private boolean valid;
}
