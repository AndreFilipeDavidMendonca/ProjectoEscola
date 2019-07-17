package com.polarising.PortalNet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.polarising.PortalNet.Forms.LoginCredentials;
import com.polarising.PortalNet.Repository.ClientRepository;
import com.polarising.PortalNet.Repository.WorkersRepository;
import com.polarising.PortalNet.jwt.JwtCreator;
import com.polarising.PortalNet.jwt.JwtResponse;
import com.polarising.PortalNet.model.Client;
import com.polarising.PortalNet.model.Workers;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	WorkersRepository workersRepository;

	@PostMapping("/home")
	public ResponseEntity<?> login(@RequestBody LoginCredentials user)
	{
		String message, jwt;
		
		if (clientRepository.existsByEmail(user.getEmail()))
		{
			Client client = clientRepository.findByEmail(user.getEmail()).get(0);
			
			if (client.getPassword().equals(user.getPassword()))
			{
				jwt = new JwtCreator().createJWT("Client");
				message = "Login bem sucedido.";
				return new ResponseEntity<>(new JwtResponse(jwt, message), HttpStatus.OK);
			}
			else
			{
				message = "Password ou email errados.";
				return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
			}
		}
		else if (workersRepository.existsByEmail(user.getEmail()))
		{
			Workers worker = workersRepository.findByEmail(user.getEmail()).get(0);
			
			if (worker.getPassword().equals(user.getPassword()))
			{
				if (worker.getRole() == "admin")
				{
					jwt = new JwtCreator().createJWT("Admin");
				}
				else
				{
					jwt = new JwtCreator().createJWT("Operator");					
				}
				message = "Login bem sucedido.";
				return new ResponseEntity<>(new JwtResponse(jwt, message), HttpStatus.OK);
			}
			else
			{
				message = "Password ou email errados.";
				return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
			}
		}
		else {
			message = "Password ou email errados.";
			return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
		}
	}
}
