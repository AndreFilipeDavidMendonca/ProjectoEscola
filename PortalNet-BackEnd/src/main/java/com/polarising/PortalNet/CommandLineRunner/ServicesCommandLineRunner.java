package com.polarising.PortalNet.CommandLineRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.polarising.PortalNet.Repository.ServiceRepository;
import com.polarising.PortalNet.model.Services;

@Component
public class ServicesCommandLineRunner implements CommandLineRunner {

	@Autowired
	ServiceRepository serviceRepository;
	
	public void run(String... args) throws Exception {
		
		serviceRepository.save(new Services("Blue Master 1", "100", "200", "1500",
				"15", 5, 100, "01-01-2019", true, "assets/img/servicesBackground0.png", "servicesBackground0.png"));
		serviceRepository.save(new Services("Blue Master 2", "50", "100", "2000",
				"3", 1, 60, "01-01-2019", true, "assets/img/servicesBackground1.png", "servicesBackground1.png"));
		serviceRepository.save(new Services("Blue Master 3", "0", "200", "0",
				"5", 8, 45, "01-01-2019", true, "assets/img/servicesBackground2.png", "servicesBackground2.png"));
		serviceRepository.save(new Services("Blue Master 4", "200", "0", "5000",
				"0", 0, 30, "01-01-2019", true, "assets/img/servicesBackground3.png", "servicesBackground3.png"));
		serviceRepository.save(new Services("Blue Master 5", "200", "10", "Minutos Ilimitados",
				"20", 2, 15, "01-01-2019", true, "assets/img/servicesBackground4.png", "servicesBackground4.png"));
		
	}
	

}
