package org.parthinfotech.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

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

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;

}
