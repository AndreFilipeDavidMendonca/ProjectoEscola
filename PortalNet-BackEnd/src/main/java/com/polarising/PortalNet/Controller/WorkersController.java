package com.polarising.PortalNet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polarising.PortalNet.Repository.WorkersRepository;
import com.polarising.PortalNet.Utilities.PortalNetHttpRequest;
import com.polarising.PortalNet.model.Workers;

@RestController
@CrossOrigin(origins = "*")
public class WorkersController {
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	WorkersRepository workersRepository;
	
	@Autowired
	PortalNetHttpRequest httpRequest;
	
	@RequestMapping(path = "/employeesTable", produces = {"application/json"})
	public List<Workers> getWorkers()
	{
		return (List<Workers>) workersRepository.findAll();
	}
	
}
