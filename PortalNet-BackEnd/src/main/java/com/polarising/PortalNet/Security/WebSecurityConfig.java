//package com.polarising.PortalNet.Security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import com.polarising.PortalNet.model.Workers;
//import com.polarising.PortalNet.Repository.ClientRepository;
//import com.polarising.PortalNet.model.Client;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	Client client;
//	
//	@Autowired
//	Workers worker;
//	
//	@Autowired
//	ClientRepository clientRepository;
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth)  //Here we configure users.
//	throws Exception {
//		auth
//		.inMemoryAuthentication()
//		.withUser("user")  //User with this name and password has a role of USER.
//		.password("{noop}password")
//		.roles("Client");
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http)  //Here we configure user access.
//	throws Exception
//	{
//		http
//			.authorizeRequests()
//				.antMatchers("/home").permitAll()
////				.antMatchers("/client/{clientId}").hasRole("Client")
////				.antMatchers("/**").hasRole("Admin")
////				.antMatchers("/**").hasRole("Operator")
//				.and().cors().disable()
//				.headers().frameOptions().disable();		
//	}
//}
