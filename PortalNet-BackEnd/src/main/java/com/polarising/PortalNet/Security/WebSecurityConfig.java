package com.polarising.PortalNet.Security;

import java.security.AuthProvider;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.polarising.PortalNet.model.Workers;
import com.polarising.PortalNet.Repository.ClientRepository;
import com.polarising.PortalNet.Repository.WorkersRepository;
import com.polarising.PortalNet.model.Client;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	Client client;
	
	@Autowired
	Workers worker;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	WorkersRepository workersRepository;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationProvider AuthProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return provider;
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth)  //Here we configure users.
//	throws Exception {
//
//	}
	
//	@Override
//	protected void configure(HttpSecurity http)  //Here we configure user access.
//	throws Exception
//	{
//		http
//			.authorizeRequests()
//				.antMatchers("/home").permitAll()
//				.antMatchers("/client/{clientId}").hasRole("Client")
//				.antMatchers("/**").hasRole("Admin")
//				.antMatchers("/**").hasRole("Operator")
//				.and().csrf().disable()
//				.headers().frameOptions().disable();		
//	}
	
//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService() {
//		
//		List<UserDetails> users = new ArrayList<>();
//		users.add(User.withDefaultPasswordEncoder().username("Bernardo").password("123").roles("Client").build());
//		return new InMemoryUserDetailsManager(users);
//	}
}
