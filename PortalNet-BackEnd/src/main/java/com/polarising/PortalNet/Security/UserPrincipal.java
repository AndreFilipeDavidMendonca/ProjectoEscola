package com.polarising.PortalNet.Security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.polarising.PortalNet.model.Client;
import com.polarising.PortalNet.model.Workers;

public class UserPrincipal implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -278630287843293797L;

	private Client client;
	
	private Workers worker;
	
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
		if(client != null)
		{
		return Collections.singleton(new SimpleGrantedAuthority("Client"));
		}
		else if (worker.getRole().equals("Employee"))
		{
		return Collections.singleton(new SimpleGrantedAuthority("Employee"));
		}
		else {
		return Collections.singleton(new SimpleGrantedAuthority("Admin"));
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
		return client.getEmail();
		}
		else
		{
			return worker.getEmail();
		}
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
