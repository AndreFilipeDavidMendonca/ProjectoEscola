package com.polarising.PortalNet.CommandLineRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.polarising.PortalNet.Repository.WorkersRepository;
import com.polarising.PortalNet.model.Workers;

@Component
public class WorkersCommandLineRunner implements CommandLineRunner{

	@Autowired
	WorkersRepository workersRepository;
	
	public void run (String... args) throws Exception {
		
		workersRepository.save(new Workers("Rui", "rui@worker.com", "worker", "yesno"));
		workersRepository.save(new Workers("Maria", "maria@worker.com", "worker", "yesno"));
		workersRepository.save(new Workers("Solange", "solange@admin.com", "admin", "yesno"));
		workersRepository.save(new Workers("Gonçalo", "rui@admin.com", "admin", "noyes"));
	}
}
