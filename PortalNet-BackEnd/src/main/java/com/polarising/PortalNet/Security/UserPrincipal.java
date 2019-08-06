package com.polarising.PortalNet.Security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.polarising.PortalNet.model.Client;
import com.polarising.PortalNet.model.Workers;

import javassist.NotFoundException;

public class UserPrincipal implements UserDetails{

	@Autowired
	PasswordEncoder passwordEncoder;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -278630287843293797L;

	private Client client;
	
	private Workers worker;
	
	private String role;
	
	public UserPrincipal(Client client) {
		super();
		this.client = client;
	}
	
	public UserPrincipal(Workers worker) {
		super();
		this.worker = worker;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		try {
		if(client != null)
		{
			this.role = "client";
			return Collections.singleton(new SimpleGrantedAuthority("CLIENT"));
		}
		else if (worker.getRole().equalsIgnoreCase("operator"))
		{
			this.role = "operator";
			return Collections.singleton(new SimpleGrantedAuthority("EMPLOYEE"));
		}
		else if (worker.getRole().equalsIgnoreCase("administrator")) {
			this.role = "administrator";
			return Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
		}
		else {
				throw new NotFoundException("Authority not found.");
		}
		} catch (NotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getPassword() {
		
		if(client != null)
		{
		return client.getPassword();
		}
		else
		{
			return worker.getPassword();
		}
	}

	@Override
	public String getUsername() {
		
		if(client != null)
		{
		return client.getClientId().toString();
		}
		else
		{
			return worker.getEmployeeId().toString();
		}
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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
		return true;
	}

}
