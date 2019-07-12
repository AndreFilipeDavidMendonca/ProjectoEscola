package com.polarising.PortalNet.Controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.polarising.PortalNet.Exceptions.NameAlreadyExistsException;
import com.polarising.PortalNet.Forms.WorkersFormRegistration;
import com.polarising.PortalNet.Repository.WorkersRepository;
import com.polarising.PortalNet.Utilities.PortalNetHttpRequest;
import com.polarising.PortalNet.model.Workers;

@RestController
@CrossOrigin(origins = "*")
public class WorkersController {

	@Autowired
	WorkersRepository workersRepository;
	
	@Autowired
	PortalNetHttpRequest httpRequest;
	
	@GetMapping(path = "/employeesTable", produces = {"application/json"})
	public ResponseEntity<?> getWorkers()
	{
		return new ResponseEntity<List<Workers>>((List<Workers>) workersRepository.findAll(), HttpStatus.OK);
	}
	
	
	
	@PostMapping(path = "/createEmployee", consumes = {"application/json"})
	public ResponseEntity<?> registerWorker(@Valid @RequestBody WorkersFormRegistration workersForm)
	{
		try {
			Workers worker = new Workers(workersForm.getName(), workersForm.getEmail()
										, workersForm.getRole(), workersForm.getPassword());
			
			List<Workers> workersList = (List<Workers>) workersRepository.findAll();
			
			for (Workers foundWorker : workersList)
			{
				if (foundWorker.getName().equals(worker.getName()))
				{
					throw new NameAlreadyExistsException("Name already exists.");
				}
			}
			
			workersRepository.save(worker);
			String message = workersForm.getName() + " was successfully created!";
			
			return new ResponseEntity<String>(message, HttpStatus.CREATED);
		} catch (NameAlreadyExistsException e) {
			String failureMessage = workersForm.getName() + " already exists.";
			return new ResponseEntity<String>("Unable to create new worker.", HttpStatus.CONFLICT);
		}
		
		
	}
}
