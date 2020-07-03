package com.lavanya.escalade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity // This tells Hibernate to make a table out of this class
public class User {
	
	@Column (name = "first_name")
	@NotBlank(message = "Ce champs est obligatoire!")
	String firstName;
	
	@Column (name = "last_name")
	@NotBlank(message = "Ce champs est obligatoire!")
	String lastName;
	
	@NotBlank(message = "Ce champs est obligatoire")
	String email;
	
	@NotNull
	@Size(min = 8, max = 14, message="veuillez rentrer un mot de passe ayant 8 à 14 caractères!")
	String password;
	
	@Column (name = "admin_rights")
	boolean admin;
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	int id;

	public User() {
		
	}
	
	public User(String firstName, String lastName, String email, String Password, boolean admin) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.admin = admin;
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
	
	@Override
	public String toString() {
		return "User(" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", admin=" + admin + "}";
	}

}
