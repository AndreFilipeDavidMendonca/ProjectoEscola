package com.polarising.PortalNet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.polarising.PortalNet.Forms.LoginCredentials;
import com.polarising.PortalNet.Repository.ClientRepository;
import com.polarising.PortalNet.Repository.WorkersRepository;
import com.polarising.PortalNet.Security.UserPrincipal;
import com.polarising.PortalNet.jwt.JwtCreator;
import com.polarising.PortalNet.jwt.JwtResponse;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	WorkersRepository workersRepository;
		
	@GetMapping("/home")
	public ResponseEntity<?> login(@RequestBody LoginCredentials user)
	{
		try{
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		
			SecurityContextHolder.getContext().setAuthentication(authentication);
		
			String jwt = new JwtCreator().createJWT(authentication);
		
			UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		
			String message = "Login bem sucedido.";
		
			return new ResponseEntity<>(new JwtResponse(jwt, message, userPrincipal.getAuthorities()), HttpStatus.OK);
		
		}
		catch(AuthenticationException e)
		{
			String message = "Password ou email errados.";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
}
