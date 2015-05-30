package com.capgemini.rest.model;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class RegistrationForm {
		
	@NotNull(message = "{reg.err.usernameempty}")
	@Size(min = 6, max = 45, message = "{reg.err.usernamesize}")
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "{reg.err.usernamepattern}")
	private String username;
	
	@NotNull(message = "{reg.err.passempty}")
	@Size(min = 5, message = "{reg.err.passsize}")
	private String password;
	
	@NotNull(message = "{reg.err.pass2empty}")
	private String passwordHelper;
	
	@NotNull(message = "{reg.err.firstnameempty}")
	@Size(max = 30, message = "{reg.err.firstnamesize}")
	private String firstname;
	
	@NotNull(message = "{reg.err.lastnameempty}")
	@Size(max = 30, message = "{reg.err.lastnamesize}")
	private String lastname;
		
	@NotNull(message = "{reg.err.emailempty}")
	@Pattern(regexp = "^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}$",
	message = "{reg.err.emailpattern}")
	private String email;
	
	@NotNull(message ="{reg.err.phoneempty}")
	@Pattern(regexp = "^(\\d{3}-?){3}", message = "{reg.err.phonepattern}")
	private String phonenumber;

	@NotNull(message = "{reg.err.groupnameempty}")
	@Size(max = 30, message = "{reg.err.groupnamesize}")
	private String groupname;
	
	@Size(max = 500)
	private String description;
	
	public String getPasswordHelper() {
		return passwordHelper;
	}

	public void setPasswordHelper(String passwordHelper) {
		this.passwordHelper = passwordHelper;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		if(phonenumber != null)
		{
			this.phonenumber = phonenumber.replace("-", "");
		}
		this.phonenumber = phonenumber;
	}

	
}
