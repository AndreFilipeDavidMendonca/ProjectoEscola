package com.polarising.PortalNet.Controller;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.List;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.polarising.PortalNet.Forms.ClientForm;
import com.polarising.PortalNet.Repository.ClientRepository;
import com.polarising.PortalNet.Repository.ServiceRepository;
import com.polarising.PortalNet.Response.ResponseMessage;
import com.polarising.PortalNet.Utilities.PortalNetHttpRequest;
import com.polarising.PortalNet.model.Client;

@RestController
@CrossOrigin(origins = "*")

public class ClientController {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	ServiceRepository serviceRepository;
	
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
		String message;
		String clientName;
				
		String today = Calendar.getInstance().getTime().toInstant().toString().substring(0, 10).replace("-", "");
		
		String random = String.format("%03d", (int) (Math.random() * 10000));
	
		String clientNumber = today + random;
		
		String entryDate = Calendar.getInstance().getTime().toString();
		
		String endContract = "08/07/2020";
		
		int numberOfServices = 1;
		
		String monthlyPay = "100€";
		
		boolean fraudulent = false;
		
		boolean status = true;
		
	
		
		Client newClient = new Client(clientNumber, clientForm.getNif(), clientForm.getName(), clientForm.getAddress(),
										clientForm.getPostalCode(), clientForm.getCity(), clientForm.getMobilePhone(),
										clientForm.getEmail(), clientForm.getGender(), clientForm.getPassword(), entryDate, 
										endContract, numberOfServices, clientForm.getServiceName(), 
										monthlyPay, fraudulent, status, clientForm.getBirthDate());
		
		List<Client> clientsList = (List<Client>) clientRepository.findAll();
		clientName = clientForm.getName();
		
		for (Client client : clientsList)
		{
			if (client.getNif() == (newClient.getNif()))
			{
				message = "Já existe um utilizador com este NIF!";
				
				return new ResponseEntity<String> (message, HttpStatus.CONFLICT);
			} 
		}
		
		clientRepository.save(newClient);	
		message = clientName + " foi registado com sucesso!";
		return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
	}
	
	@PutMapping(path = "/client/{clientId}", consumes = {"application/json"})
	public ResponseEntity<?> updateClient(@PathVariable int clientId, @RequestBody Client client)
	{
		try{
		
//			Gson gson = new Gson();
		
		Client clientToUpdate = clientRepository.findByClientId(clientId).get(0);
//		JSONAssert.assertEquals("{clientId:" + client.getClientId() + "}", gson.toJson(clientRepository.findByClientId(clientId).get(0)), false);
		
		String newServicePay = Float.toString(serviceRepository.findByName(client.getServiceName()).get(0).getPrice());
		
		clientToUpdate.setClientId(client.getClientId());
		clientToUpdate.setAddress(client.getAddress());
		clientToUpdate.setCity(client.getCity());
		clientToUpdate.setEmail(client.getEmail());
		clientToUpdate.setFraudulent(client.isFraudulent());
		clientToUpdate.setMobilePhone(client.getMobilePhone());
		clientToUpdate.setMonthlyPay(newServicePay);
		clientToUpdate.setName(client.getName());
		clientToUpdate.setPassword(client.getPassword());
		clientToUpdate.setPostalCode(client.getPostalCode());
		clientToUpdate.setServiceName(client.getServiceName());
		clientToUpdate.setStatus(client.isStatus());
		
		//clientRepository.deleteById(clientId);
		clientRepository.save(clientToUpdate);
		String message = "Atualização bem sucedida.";
		
		return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
		}
		catch(ClassCastException e)
		{
			return new ResponseEntity<Client>(client, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(IndexOutOfBoundsException e)
		{
			String message = "O nome do novo serviço não se encontra na lista de serviços disponíveis.";
			return new ResponseEntity<String>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
//		catch(JSONException e)
//		{
//			String message = "Id de cliente não existe.";
//			return new ResponseEntity<String>(message,HttpStatus.INTERNAL_SERVER_ERROR);
//		}
	}
}