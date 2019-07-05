package com.polarising.PortalNet.CommandLineRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.polarising.PortalNet.Repository.ClientRepository;
import com.polarising.PortalNet.model.Client;

@Component
public class ClientCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	private ClientRepository clientRepository;

	public void run(String... args) throws Exception {
		
		clientRepository.save(new Client(1, 1111111, "Andre", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, "andre@polarising.com", "male",
				"bernardoAdmin", "27/06/2019", "27/06/2020", 2, "Pacote 4 Serviços",  "100€", true, true, "18/01/1989"));
		clientRepository.save(new Client(2, 222222222, "Bernardo", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, "bernardo@polarising.com", "male",
				"bernardoAdmin", "27/06/2019", "27/06/2020", 2, "Pacote 4 Serviços",  "100€", true, true, "25/11/1995"));
		clientRepository.save(new Client(3, 333333333, "Rui", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, "rui@polarising.com", "male",
				"bernardoAdmin", "27/06/2019", "27/06/2020", 2, "Pacote 4 Serviços",  "100€", false, false, "18/01/1989"));
		clientRepository.save(new Client(4, 444444444, "Daniel", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, "bernardo@polarising.com", "male",
				"bernardoAdmin", "27/06/2019", "27/06/2020", 2, "Pacote 4 Serviços",  "100€", false, true, "18/01/1989"));
		clientRepository.save(new Client(5, 555555555, "Eliana", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, "bernardo@polarising.com", "male",
				"bernardoAdmin", "27/06/2019", "27/06/2020", 2, "Pacote 4 Serviços",  "100€", true, false, "18/01/1989"));
		clientRepository.save(new Client(6, 666666666, "Perdiz", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, "bernardo@polarising.com", "male",
				"bernardoAdmin", "27/06/2019", "27/06/2020", 2, "Pacote 4 Serviços",  "100€", true, true, "18/01/1989"));
		clientRepository.save(new Client(7, 7111111, "Renato", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, "bernardo@polarising.com", "male",
				"bernardoAdmin", "27/06/2019", "27/06/2020", 2, "Pacote 4 Serviços",  "100€", true, true, "18/01/1989"));
		clientRepository.save(new Client(8, 8111111, "Soalnge", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, "bernardo@polarising.com", "male",
				"bernardoAdmin", "27/06/2019", "27/06/2020", 2, "Pacote 4 Serviços",  "100€", true, true, "18/01/1989"));
		clientRepository.save(new Client(9, 9111111, "Cecília", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, "bernardo@polarising.com", "male",
				"bernardoAdmin", "27/06/2019", "27/06/2020", 2, "Pacote 4 Serviços",  "100€", false, true, "18/01/1989"));
		clientRepository.save(new Client(10, 0111111, "Nuno", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, "bernardo@polarising.com", "male",
				"bernardoAdmin", "27/06/2019", "27/06/2020", 2, "Pacote 4 Serviços",  "100€", true, false, "18/01/1989"));
		clientRepository.save(new Client(11, 1211111, "César", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, "bernardo@polarising.com", "male",
				"bernardoAdmin", "27/06/2019", "27/06/2020", 2, "Pacote 4 Serviços",  "100€", true, true, "18/01/1989"));
		clientRepository.save(new Client(13, 1311111, "Felipe", "Rua da Alegria", "1000-245",
				"Lisboa", 911111111, "bernardo@polarising.com", "male",
				"bernardoAdmin", "27/06/2019", "27/06/2020", 2, "Pacote 4 Serviços",  "100€", true, false, "18/01/1989"));
		
		
	}
	

}
