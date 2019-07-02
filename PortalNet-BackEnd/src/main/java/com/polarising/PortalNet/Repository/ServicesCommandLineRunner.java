package com.polarising.PortalNet.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.polarising.PortalNet.model.Services;

@Component
public class ServicesCommandLineRunner implements CommandLineRunner {

	@Autowired
	ServiceRepository serviceRepository;
	
	public void run(String... args) throws Exception {
		
		serviceRepository.save(new Services("Pacote 1", "100 Mbps", "200", "Minutos Ilimitados",
				"Minutos Ilimitados", 1, 100, "01-01-2019", true));
		serviceRepository.save(new Services("Pacote 2", "50 Mbps", "100", "Minutos Ilimitados",
				"Minutos Ilimitados", 1, 60, "01-01-2019", true));
		serviceRepository.save(new Services("Pacote 3", "Sem Internet", "200", "Minutos Ilimitados",
				"Minutos Ilimitados", 1, 45, "01-01-2019", true));
		serviceRepository.save(new Services("Pacote 4", "200 Mbps", "0", "Minutos Ilimitados",
				"Minutos Ilimitados", 1, 30, "01-01-2019", true));
		serviceRepository.save(new Services("Pacote 5", "200 Mbps", "10", "Minutos Ilimitados",
				"Minutos Ilimitados", 1, 15, "01-01-2019", true));
		
	}
	

}
