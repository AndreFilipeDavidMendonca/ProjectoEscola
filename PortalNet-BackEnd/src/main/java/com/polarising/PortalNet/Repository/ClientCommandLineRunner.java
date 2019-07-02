package com.polarising.PortalNet.Repository;

import org.apache.commons.logging.Log;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.polarising.PortalNet.model.Client;
//import com.polarising.PortalNet.model.string;

import ch.qos.logback.classic.Logger;

@Component
public class ClientCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	private ClientRepository clientRepository;

	public void run(String... args) throws Exception {
		
		clientRepository.save(new Client(12345, 1111111, "Andre", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, "bernardo@polarising.com", "male",
				"bernardoAdmin", "27/06/2019", "27/06/2020", 2, "Pacote 4 Serviços",  "100€", true, true, "18/01/1989"));
		clientRepository.save(new Client(12345, 1111111, "Andre", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, "bernardo@polarising.com", "male",
				"bernardoAdmin", "27/06/2019", "27/06/2020", 2, "Pacote 4 Serviços",  "100€", true, true, "18/01/1989"));
		
		
	}
	

}
