package com.polarising.PortalNet.TesteAddClients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.polarising.PortalNet.model.Client;

public class ClientCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	ClientRepository clientRepository;

	public void run(String... args) throws Exception {
		
		clientRepository.save(new Client(1111111, "Bernardo", "Rua da Alegria", 1000,
				"Lisboa", 911111111, "bernardo@polarising.com",
				"password", "27/06/2019", 100, 200, true, true, false));
		
	}
	
	

}
