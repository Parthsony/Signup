package org.parthinfotech.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;

	private String lastName;

	@Column(unique = true)
	private String email;

	private String password;

}
