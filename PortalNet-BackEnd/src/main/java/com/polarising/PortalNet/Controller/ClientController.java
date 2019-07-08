package com.polarising.PortalNet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.polarising.PortalNet.Repository.ClientRepository;
import com.polarising.PortalNet.Utilities.PortalNetHttpRequest;
import com.polarising.PortalNet.model.Client;

@RestController
@CrossOrigin(origins = "*")
public class ClientController {
	

	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	PortalNetHttpRequest httpRequest;
	
	@GetMapping(path = "/clientsTable", produces= {"application/json"})
	public ResponseEntity<?> getClients()
	{	
		return new ResponseEntity<List<Client>>((List<Client>) clientRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/client/{clientId}", produces= {"application/json"})
	public ResponseEntity<?> getClientById(@PathVariable int clientId)
	{	
		return new ResponseEntity<List<Client>>((List<Client>) clientRepository.findByClientId(clientId), HttpStatus.OK);
	}
	
}