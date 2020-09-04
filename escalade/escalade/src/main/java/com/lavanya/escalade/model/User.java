package com.lavanya.escalade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.lavanya.escalade.validation.PasswordMatches;
import com.lavanya.escalade.validation.ValidEmail;

@PasswordMatches
@Entity // This tells Hibernate to make a table out of this class
public class User {
	
	@Column (name = "first_name")
	@NotBlank(message = "Ce champs est obligatoire!")
	private String firstName;
	
	@Column (name = "last_name")
	@NotBlank(message = "Ce champs est obligatoire!")
	private String lastName;
	
	@NotBlank(message = "Ce champs est obligatoire")
	@ValidEmail
	private String email;
	
	@NotNull
	@Size(min = 8, max = 14, message="veuillez rentrer un mot de passe ayant 8 à 14 caractères!")
	private String password;
	
	private String matchingPassword;
	
	@Column (name = "admin_rights")
	private boolean admin;
	
	private String roles;
	private boolean isActive;
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	int id;

	public User() {
		
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "User(" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", admin=" + admin + ", roles=" + roles + ", isActive=" + isActive + "}";
	}

}
