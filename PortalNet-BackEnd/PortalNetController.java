package com.polarising.PortalNet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polarising.PortalNet.TesteAddClients.ClientCommandLineRunner;
import com.polarising.PortalNet.TesteAddClients.ClientRepository;
import com.polarising.PortalNet.model.Client;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PortalNetController {
	
	@Autowired
	ClientRepository clientRepository;
	
	@RequestMapping(path = "/clients")
	public Iterable<Client> insertClient()
	{
		return clientRepository.findAll();
	}
}
