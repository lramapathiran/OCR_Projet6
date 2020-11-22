package com.lavanya.escalade.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.lavanya.escalade.model.User;

public class MyUserDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	public String firstName;
	public String lastName;
	private String userName;
	private String password;
	private boolean isActive;
	private List<GrantedAuthority> authorities;
	private int id;

	public MyUserDetails(User user) {
		this.userName = user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.password = user.getEncodedPassword();
		this.isActive = user.isActive();
		this.authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRoles()));
		this.id = user.getId();
	}
	
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getId() {
		return id;
	}


	public MyUserDetails() {}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {

		return userName;
	}	
	

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isActive;
	}




}
