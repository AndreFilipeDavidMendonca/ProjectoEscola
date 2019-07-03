package com.polarising.PortalNet.Controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.apache.tomcat.util.json.JSONParser;
//import org.json.JSONArray;
//import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.polarising.PortalNet.Repository.ClientCommandLineRunner;
import com.polarising.PortalNet.Repository.ClientRepository;
import com.polarising.PortalNet.Utilities.PortalNetHttpRequest;
import com.polarising.PortalNet.model.Client;

@RestController
@CrossOrigin(origins = "*")
public class ClientController {
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	PortalNetHttpRequest httpRequest;
	
	@RequestMapping(path = "/clientsTable", produces= {"application/json"})
	public List<Client> getClients()
	{	
		return (List<Client>) clientRepository.findAll();
	}
	
	@GetMapping("/client/name/{name}")
	public List<Client> findByName(@PathVariable String name)
	{
		return clientRepository.findByName(name);
	}
	
	@GetMapping("/client/postalCode/{postalCode}")
	public List<Client> findByPostalCode(@PathVariable int postalCode)  //Neste método, adiciono como teste o uso de um get ao Url /client/name/Andre, e faço print na consola.
	{
		String string = httpRequest.get("/client/name/Andre");
		System.out.println(string);
		return clientRepository.findByPostalCode(postalCode);
	}	
	
	@GetMapping("client/name/{name}/exists")
	public boolean existsByName(@PathVariable String name)
	{
		if (clientRepository.existsByName(name))
		{
			return true;
		}
		else{
			return false;
		}
	}
}
