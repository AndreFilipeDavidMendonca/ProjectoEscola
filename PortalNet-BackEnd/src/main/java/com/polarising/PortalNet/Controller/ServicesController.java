package com.polarising.PortalNet.Controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polarising.PortalNet.Forms.ClientForm;
import com.polarising.PortalNet.Forms.ServiceForm;
import com.polarising.PortalNet.Repository.ServiceRepository;
import com.polarising.PortalNet.Utilities.PortalNetHttpRequest;
import com.polarising.PortalNet.model.Client;
import com.polarising.PortalNet.model.Services;

@RestController
@CrossOrigin(origins = "*")
public class ServicesController {
	
	@Autowired
	ServiceRepository serviceRepository;
	
	@Autowired
	PortalNetHttpRequest httpRequest;
	
	@RequestMapping(path = "/servicesTable", produces= {"application/json"})
	public List<Services> getServices()
	{
		return (List<Services>) serviceRepository.findAll();
	}
	
	@PostMapping(path = "/createService", consumes = {"application/json"})
	public ResponseEntity<?> registerService(@RequestBody ServiceForm serviceForm)
	{	
		String message;
		String name;
		String creationDate = Calendar.getInstance().getTime().toString();
		boolean status = true;
		
		
	
		
		Services newService = new Services(serviceForm.getName(), serviceForm.getTv(), serviceForm.getInternet(), serviceForm.getMobilePhone(),
											serviceForm.getPhone(), serviceForm.getLoyalty(), serviceForm.getPrice(), creationDate, status);
		
		List<Services> servicesList = (List<Services>) serviceRepository.findAll();
		name = serviceForm.getName();
		
		for (Services service : servicesList)
		{
			if (service.getName().equals(newService.getName()))
			{
				message = "Já existe um serviço com este nome!";
				
				return new ResponseEntity<String> (message, HttpStatus.CONFLICT);
			} 
		}
		
		serviceRepository.save(newService);		
		message = "O serviço " + name + " foi registado com sucesso!";
		return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
	}
}


