package com.PortalNet.PortalNet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polarising.PortalNet.TesteAddClients.ClientRepository;
import com.polarising.PortalNet.model.Client;

@RestController
public class PortalNetController {

	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	Client client;
	
	@RequestMapping("/client")
	public Iterable<Client> insertClient()
	{
		return clientRepository.findAll();
	}
}
