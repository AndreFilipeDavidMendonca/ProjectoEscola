package com.polarising.PortalNet.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.polarising.PortalNet.Forms.WorkersForm;
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
	
	
	
	@PostMapping(path = "/createEmployee")
	public ResponseEntity<?> registerWorker(@Valid @RequestBody WorkersForm workersForm)
	{
		try {
			Workers worker = new Workers(workersForm.getName(), workersForm.getEmail()
										, workersForm.getRole(), workersForm.getPassword());
			
			List<Workers> workersList = (List<Workers>) workersRepository.findAll();
			
/*			if (worker.getName().equals("") || worker.getEmail().equals("") || worker.getPassword().equals("") || worker.getRole().equals("") ||
					worker.getName().equals(null) || worker.getEmail().equals(null) || worker.getPassword().equals(null) || worker.getRole().equals(null))
			{
				return new ResponseEntity<String>("Unable to create new worker.", HttpStatus.PARTIAL_CONTENT);
			}*/
			
			for (Workers foundWorker : workersList)
			{
				if (foundWorker.getName().equals(worker.getName()))
				{
					return new ResponseEntity<String>("User already exists.", HttpStatus.FOUND);
				}
			}
			
			workersRepository.save(worker);
			
			return new ResponseEntity<Workers>(worker, HttpStatus.CREATED);
		} catch (RestClientException e) {
			
			return new ResponseEntity<String>("Unable to create new worker.", HttpStatus.PARTIAL_CONTENT);
		}
		
		
	}
}
