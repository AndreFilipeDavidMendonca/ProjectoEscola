package com.polarising.PortalNet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polarising.PortalNet.Repository.ServiceRepository;
import com.polarising.PortalNet.Utilities.PortalNetHttpRequest;
import com.polarising.PortalNet.model.Services;

@RestController
@CrossOrigin(origins = "*")
public class ServicesController {
	
	@Autowired
	ServiceRepository serviceRepository;
	
	@Autowired
	PortalNetHttpRequest httpRequest;
	
	@RequestMapping(path = "/servicesTable", produces= {"application/json"})
	public ResponseEntity<?> getServices()
	{
		return new ResponseEntity<List<Services>>((List<Services>) serviceRepository.findAll(), HttpStatus.OK);
	}

}
