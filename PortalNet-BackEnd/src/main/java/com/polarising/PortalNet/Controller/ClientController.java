package com.polarising.PortalNet.Controller;

import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.polarising.PortalNet.Exceptions.NifAlreadyExistsException;
import com.polarising.PortalNet.Forms.ClientForm;
import com.polarising.PortalNet.Repository.ClientRepository;
import com.polarising.PortalNet.Utilities.PortalNetHttpRequest;
import com.polarising.PortalNet.model.Client;

@RestController
@CrossOrigin(origins = "*")
public class ClientController {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	PortalNetHttpRequest httpRequest;
	
	@GetMapping(path = "/clientsTable", produces= {"application/json"})
	public ResponseEntity<?> getClients()
	{	
		return new ResponseEntity<List<Client>>((List<Client>) clientRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/client/{clientId}", produces= {"application/json"})
	public ResponseEntity<?> getClientById(@PathVariable int clientId)
	{	
		return new ResponseEntity<List<Client>>((List<Client>) clientRepository.findByClientId(clientId), HttpStatus.OK);
	}
	
	
	@PostMapping(path = "/registration", consumes = {"application/json"})
	public ResponseEntity<?> registerClient(@RequestBody ClientForm clientForm)
	{
		try{

		String today = Calendar.getInstance().getTime().toInstant().toString().substring(0, 10).replace("-", "");
		
		String random = String.format("%03d", (int) (Math.random() * 10000));
	
		String clientNumber = today + random;
		
		String entryDate = Calendar.getInstance().getTime().toString();
		
		String endContract = "08/07/2020";
		
		int numberOfServices = 1;
		
		String monthlyPay = "100€";
		
		boolean fraudulent = false;
		
		boolean status = true;
		
		String message;
		
		Client newClient = new Client(clientNumber, clientForm.getNif(), clientForm.getName(), clientForm.getAddress(),
										clientForm.getPostalCode(), clientForm.getCity(), clientForm.getMobilePhone(),
										clientForm.getEmail(), clientForm.getGender(), clientForm.getPassword(), entryDate, 
										endContract, numberOfServices, clientForm.getServiceName(), 
										monthlyPay, fraudulent, status, clientForm.getBirthDate());
		
		List<Client> clientsList = (List<Client>) clientRepository.findAll();
		
		for (Client client : clientsList)
		{
			if (client.getNif() == (newClient.getNif()))
			{
				throw new NifAlreadyExistsException("Client already exists.");
			}
		}
		
		clientRepository.save(newClient);
		
		message = "Client was successfully created!";
		
		return new ResponseEntity<String> (message, HttpStatus.CREATED);
		}
		catch (NifAlreadyExistsException e)
		{
			String failureMessage = "Client already exists.";
			return new ResponseEntity<String> (failureMessage, HttpStatus.CONFLICT);
		}
	}
}