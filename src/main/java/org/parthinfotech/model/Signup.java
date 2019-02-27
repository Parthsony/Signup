package org.parthinfotech.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.parthinfotech.controller.ForgotPasswordRequest;

import lombok.Getter;
import lombok.Setter;

/**
 * Model class that represent primary details of details of a new user.
 * 
 * @author Parth Soni
 *
 */
@Getter
@Setter
@Entity
@Table(name = "signup")
public class Signup implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String requestId;

	private String firstName;

	private String lastName;

	@Column(unique = true)
	private String email;

	private String password;

	private boolean emailVerified;

	private Date created;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<ForgotPasswordRequest> forgotPasswordRequestList;

}
